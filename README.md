# gwendolen-ros-turtlebot3
Examples of a Gwendolen agent autonomously controlling a turtlebot3 (burger).

Requires [gwendolen-rosbridge](https://github.com/autonomy-and-verification-uol/gwendolen-rosbridge) to be installed and working.

Also requires turtlebot3-ros to be installed. Make sure to install [turtlebot3 main files](http://emanual.robotis.com/docs/en/platform/turtlebot3/pc_setup/) and [turtlebot3 simulation files](http://emanual.robotis.com/docs/en/platform/turtlebot3/simulation/).

**Turtlebot3-ros requires Ubuntu 16.04 and ROS Kinetic.**

Since we are using the burger model of turtlebot3, make sure to follow the steps described [here](http://emanual.robotis.com/docs/en/platform/turtlebot3/export_turtlebot3_model/).

## Fake node 
Fake node is a simple representation of a turtlebot3 in rviz, where the Gwendolen agents firt moves 2 meters, and then keeps moving 2 meters every 2000 milliseconds.

To run the fake node simulation:
1. Copy the launch file `launch/turtlebot3_fake_with_rosbridge.launch` to `~/catkin_ws/src/turtlebot3_simulations/turtlebot3_fake/launch`
   * Make sure you either `source ~/catkin_ws/devel/setup.bash` everytime you wish to launch turtlebot on a new terminal, or add it to your `~/.bashrc`
2.  Copy the `src` folder to MCAPL root
3. Launch the fake node in ros `roslaunch turtlebot3_fake turtlebot3_fake_with_rosbridge.launch`
4. In Eclipse, go to `src/examples/gwendolen/ros/turtlebot3/fakenode`, right-click turtlebot3.ail, select run as > run configurations, type run-AIL in the search box (should be there if MCAPL was installed correctly), and click on run
   * The robot should start moving forward in the `rviz` window

## Empty world
Empty world is an empty Gazebo world with a turtlebot3.

To run the empty world simulation:
1. Copy the launch file `launch/turtlebot3_empty_world_with_rosbridge.launch` to `~/catkin_ws/src/turtlebot3_simulations/turtlebot3_gazebo/launch`
   * Make sure you either `source ~/catkin_ws/devel/setup.bash` everytime you wish to launch turtlebot on a new terminal, or add it to your `~/.bashrc`
2.  Copy the `src` folder to MCAPL root
3. Launch the empty world in ros `roslaunch turtlebot3_gazebo turtlebot3_fake_with_rosbridge.launch`
4. In Eclipse, go to `src/examples/gwendolen/ros/turtlebot3/emptyworld`, right-click turtlebot3.ail, select run as > run configurations, type run-AIL in the search box (should be there if MCAPL was installed correctly), and click on run
   * The robot should start slowly moving forward, stopping, and repeating the movement in the `gazebo` window

## Turtlebot world (with move_base for moving with coordinates)
Turtlebot world is a Gazebo world with the turtlebot logo as obstacles and a turtlebot3.

To run the turtlebot world simulation:
1. Copy the launch file `launch/turtlebot3_world_with_rosbridge.launch` to `~/catkin_ws/src/turtlebot3_simulations/turtlebot3_gazebo/launch`
   * Make sure you either `source ~/catkin_ws/devel/setup.bash` everytime you wish to launch turtlebot on a new terminal, or add it to your `~/.bashrc`
2. Copy the map file `maps/map.yaml` to `~/catkin_ws/src/turtlebot3/turtlebot3_navigation/maps`
3. Copy the folder `simple_navigation_goals` to `~/catkin_ws/src/`
4. Recompile your catkin workspace by going to `~/catkin_ws/` and running `catkin_make`
5.  Copy the `src` folder to MCAPL root
6. Launch the turtlebot world in ros `roslaunch turtlebot3_gazebo turtlebot3_world_with_rosbridge.launch`
7. In Eclipse, go to `src/examples/gwendolen/ros/turtlebot3/turtlebotworld`, right-click turtlebot3.ail, select run as > run configurations, type run-AIL in the search box (should be there if MCAPL was installed correctly), and click on run
   * The robot will patrol the four areas in the middle of the pillars

## Turtlebot house (with move_base for moving with coordinates)
Turtlebot house is Gazebo world with a house and a turtlebot3.

To run the turtlebot house:
1. ...
