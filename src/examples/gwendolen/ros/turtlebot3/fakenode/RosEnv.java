package gwendolen.ros.turtlebot3.fakenode;

import com.fasterxml.jackson.databind.JsonNode;

import ail.mas.DefaultEnvironment;
import ail.syntax.Action;
import ail.syntax.NumberTerm;
import ail.syntax.Unifier;
import ail.util.AILexception;
import ros.Publisher;
import ros.RosBridge;
import ros.RosListenDelegate;
import ros.SubscriptionRequestMsg;
import ros.msgs.geometry_msgs.Twist;
import ros.msgs.geometry_msgs.Vector3;
import ros.msgs.std_msgs.PrimitiveMsg;
import ros.tools.MessageUnpacker;
import ros.tools.PeriodicPublisher;



public class RosEnv extends DefaultEnvironment{
	static final String logname = "gwendolen.ros.turtlebot3.fakenode.RosEnv";
	
	RosBridge bridge = new RosBridge();
	
	/**
	 * Constructor.  Decides upon the number of humans, buildings and rubble.
	 */
	public RosEnv() {
		super();
		
		bridge.connect("ws://localhost:9090", true);
		System.out.println("Environment started, connection with ROS established.");
		
		bridge.subscribe(SubscriptionRequestMsg.generate("/ros_to_java")
				.setType("std_msgs/String")
				.setThrottleRate(1)
				.setQueueLength(1),
			new RosListenDelegate() {

				public void receive(JsonNode data, String stringRep) {
					MessageUnpacker<PrimitiveMsg<String>> unpacker = new MessageUnpacker<PrimitiveMsg<String>>(PrimitiveMsg.class);
					PrimitiveMsg<String> msg = unpacker.unpackRosMessage(data);
					System.out.println(msg.data);
				}
			}
	);

	
	}
	
	/*
	 * (non-Javadoc)
	 * @see ail.mas.DefaultEnvironment#executeAction(java.lang.String, ail.syntax.Action)
	 */
	public Unifier executeAction(String agName, Action act) throws AILexception {
		String actionname = act.getFunctor();
		if (actionname.equals("helloros")) {
			helloros();
		} else if (actionname.equals("move")) {
			NumberTerm lx = (NumberTerm) act.getTerm(0);
			NumberTerm ly = (NumberTerm) act.getTerm(1);
			NumberTerm lz = (NumberTerm) act.getTerm(2);
			NumberTerm ax = (NumberTerm) act.getTerm(3);
			NumberTerm ay = (NumberTerm) act.getTerm(4);
			NumberTerm az = (NumberTerm) act.getTerm(5);
			move(lx.solve(),ly.solve(),lz.solve(),ax.solve(),ay.solve(),az.solve());
		} else if (actionname.equals("keepmoving")) {
		NumberTerm period = (NumberTerm) act.getTerm(0);
		NumberTerm lx = (NumberTerm) act.getTerm(1);
		NumberTerm ly = (NumberTerm) act.getTerm(2);
		NumberTerm lz = (NumberTerm) act.getTerm(3);
		NumberTerm ax = (NumberTerm) act.getTerm(4);
		NumberTerm ay = (NumberTerm) act.getTerm(5);
		NumberTerm az = (NumberTerm) act.getTerm(6);
		keepmoving((int) period.solve(),lx.solve(),ly.solve(),lz.solve(),ax.solve(),ay.solve(),az.solve());
	}
		
		return super.executeAction(agName, act);
	}
	
	public void helloros() {
		Publisher pub = new Publisher("/java_to_ros", "std_msgs/String", bridge);
		
		for(int i = 0; i < 100; i++) {
			pub.publish(new PrimitiveMsg<String>("hello from gwendolen " + i));
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void move(double lx, double ly, double lz, double ax, double ay, double az) {
		Publisher cmd_vel = new Publisher("/cmd_vel", "geometry_msgs/Twist", bridge);
		
		Vector3 linear = new Vector3(lx,ly,lz);
		Vector3 angular = new Vector3(ax,ay,az);
		cmd_vel.publish(new Twist(linear, angular));
	}
	
	public void keepmoving(int period, double lx, double ly, double lz, double ax, double ay, double az) {
		PeriodicPublisher cmd_vel = new PeriodicPublisher("/cmd_vel", "geometry_msgs/Twist", bridge);
		
		Vector3 linear = new Vector3(lx,ly,lz);
		Vector3 angular = new Vector3(ax,ay,az);
		cmd_vel.beginPublishing(new Twist(linear, angular), 2000);
	}


}
