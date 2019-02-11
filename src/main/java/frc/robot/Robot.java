/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot;

//import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj.buttons.JoystickButton;

// Camera imports
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.cscore.VideoSink;
import edu.wpi.cscore.VideoSource.ConnectionStrategy;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	private static final String kDefaultAuto = "Default";
	private static final String kCustomAuto = "My Auto";
	private String m_autoSelected;
	private SendableChooser<String> m_chooser = new SendableChooser<>();

	Spark leftmotor = new Spark(0);
	Spark rightmotor = new Spark(1);
	Joystick controller = new Joystick(1);
	// Joystick controller2 = new Joystick(0);
	Servo arm = new Servo(2);
	

	DifferentialDrive driver = new DifferentialDrive(leftmotor, rightmotor);


	UsbCamera camera1;
	UsbCamera camera2;
	VideoSink server;
	boolean camera1Selected;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_chooser.addDefault("Default Auto", kDefaultAuto);		 
		m_chooser.addObject("My Auto", kCustomAuto);
		SmartDashboard.putData("Auto choices", m_chooser);

		camera1 = CameraServer.getInstance().startAutomaticCapture(0);
		// camera2 = CameraServer.getInstance().startAutomaticCapture(1);
		server = CameraServer.getInstance().getServer();

		camera1.setConnectionStrategy(ConnectionStrategy.kKeepOpen);
		// camera2.setConnectionStrategy(ConnectionStrategy.kKeepOpen);
		camera1Selected = true;
		server.setSource(camera1);
	}
	
	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional comparisons to
	 * the switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		m_autoSelected = m_chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		// do we need this comment?
		System.out.println("Auto selected: " + m_autoSelected);
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		switch (m_autoSelected) {
			case kCustomAuto:
				// Put custom auto code here
				break;
			case kDefaultAuto:
			default:
				// Put default auto code here
				break;   
		}
	}

	/**
	 * This function is called periodically during operator control. I made it so that in the program the 
	 */

	@Override
	public void teleopPeriodic() {		

		// control tracks
		double forwardSpeed;
		double rotationSpeed;

		double xAxis = controller.getRawAxis(0);
		double yAxis = controller.getRawAxis(1);
	  	boolean precisionButton = controller.getRawButton(1);
		
		// set drive speed
		if (!precisionButton){
			forwardSpeed = yAxis;
			rotationSpeed = xAxis;
		} else{
			forwardSpeed = yAxis*0.7;
			rotationSpeed = xAxis*0.5;
		}
		driver.arcadeDrive(forwardSpeed, rotationSpeed, true);

		// control arm
		// double yAxis2 = controller2.getRawAxis(1);
		// if (yAxis2 > 0) {
		// 	servomotor.set(1);
		// } else if (yAxis2 < 0) {
		// 	servomotor.set(0);
		 
		boolean btn2 = controller.getRawButton(2);
		boolean btn3 = controller.getRawButton(3);
		if (btn2) {

		} else if (btn3) {

		} else {
			
		}

		
		// // camera button
		// boolean cameraButton = controller.getRawButton(1);
		// if (camera1Selected) {
		// 	server.setSource(camera2);
		// 	camera1Selected = false;
		// } else {
		// 	server.setSource(camera1);
		// 	camera1Selected = true;
		// }
		
	}
}
