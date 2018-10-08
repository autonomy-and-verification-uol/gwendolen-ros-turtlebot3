#! /usr/bin/env python
from __future__ import print_function

import rospy
import actionlib

from move_base_msgs.msg import MoveBaseAction, MoveBaseGoal
from geometry_msgs.msg import Vector3

def send_to_movebase(x,y,z):

    client = actionlib.SimpleActionClient('move_base',MoveBaseAction)
    client.wait_for_server()

    goal = MoveBaseGoal()
    goal.target_pose.header.frame_id = "map"
    goal.target_pose.header.stamp = rospy.Time.now()
    goal.target_pose.pose.position.x = x
    goal.target_pose.pose.position.y = y
    goal.target_pose.pose.position.z = z
    goal.target_pose.pose.orientation.w = 1.0

    client.send_goal(goal)
    wait = client.wait_for_result()
    if not wait:
        rospy.logerr("Action server not available!")
        rospy.signal_shutdown("Action server not available!")
    else:
        return client.get_result()

def goal_movebase(data):
	
    #rospy.loginfo(rospy.get_caller_id() + "I heard %f and %f and %f",data.x,data.y,data.z)
    try:
        result = send_to_movebase(data.x,data.y,data.z)
        if result:
            rospy.loginfo("Goal execution done!")
    except rospy.ROSInterruptException:
        rospy.loginfo("Navigation test finished.")    

def movebase_client():
	
	rospy.init_node('movebase_client_py', anonymous=True)
	
	rospy.Subscriber("gwendolen_to_move_base", Vector3, goal_movebase)
	
	rospy.spin()

if __name__ == '__main__':
	movebase_client()
