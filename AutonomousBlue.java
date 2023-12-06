package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "AutonomousBlue ")
public class AutonomousBlue extends LinearOpMode {
    
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private DcMotor armHeight =null;
    private Servo leftClaw = null;
    private Servo rightClaw = null;
    private Servo trigger = null;
    private double leftClawPos = 0;
    private double rightClawPos = 0;
    
  /**
   * This function is executed when this OpMode is selected from the Driver Station.
   */
      @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        leftDrive  = hardwareMap.get(DcMotor.class, "Motor3");
        rightDrive = hardwareMap.get(DcMotor.class, "Motor2");
        armHeight = hardwareMap.get(DcMotor.class , "Motor4");
        leftClaw = hardwareMap.get(Servo.class , "servo1"       );
        rightClaw = hardwareMap.get(Servo.class , "servo2");
        trigger = hardwareMap.get(Servo.class, "servo3");
        
        

        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
        // Pushing the left stick forward MUST make robot go forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();
        
        
        rightClaw.setPosition(.9);
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
                
            // Setup a variable for each drive wheel to save power level for telemetry
            double leftPower;
            double rightPower;
            
          
        
    

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            
            telemetry.addData("servohd",leftClaw.getPosition());
            telemetry.addData("truggeer engaged",gamepad1);
            telemetry.addData("arhmd:", armHeight.getCurrentPosition());
            telemetry.update();
            
            };
            
        }
        
        private void moveArm(int position, double power) 
            {                  
                armHeight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                armHeight.setTargetPosition(position);
                armHeight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                armHeight.setPower(power);
                armHeight.setPower(0);
                armHeight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                armHeight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            };
            /*private void moveRightDrive(int position, double power) 
            {                  
                rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                rightDrive.setTargetPosition(position);
                rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightDrive.setPower(power);
                rightDrive.setPower(0);
                rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            };
            private void moveLeftDrive(int position, double power) 
            {                  
                leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                leftDrive.setTargetPosition(position);
                leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                leftDrive.setPower(power);
                leftDrive.setPower(0);
                leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            };
            
     
    }
    
 
