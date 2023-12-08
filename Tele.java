
package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/*IMPORTANT

Hardware names

features */


@TeleOp(name="Basic Buffoon", group="Linear OpMode")
public class Beuffoons extends LinearOpMode {

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
    private DcMotor armHeight2 = null;

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
        leftClaw = hardwareMap.get(Servo.class , "servo5");
        rightClaw = hardwareMap.get(Servo.class , "servo4");
        trigger = hardwareMap.get(Servo.class, "servo3");
        armHeight2 = hardwareMap.get(DcMotor.class, "Motor1");
        
        

        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
        // Pushing the left stick forward MUST make robot go forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();
        
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double leftPower;
            double rightPower;
            
            if(gamepad2.y || gamepad1.y){
                armHeight.setPower(1);
                armHeight2.setPower(-1);
                    }
                    armHeight.setPower(0);
                    armHeight2.setPower(0);
                    
            if(gamepad2.x || gamepad1.x){
                armHeight.setPower(-1);
                armHeight2.setPower(1);
                
                    }
                armHeight.setPower(0);
                armHeight2.setPower(0);
                
            if(gamepad2.left_bumper || gamepad1.left_bumper){
                leftClaw.setDirection(Servo.Direction.FORWARD);
                leftClaw.setPosition(-.1);
                //gamepad2.left_bumper = false;
                //gamepad1.left_bumper = false;
            } else if(gamepad2.left_trigger!= 0 || gamepad1.left_trigger!= 0){
                leftClaw.setDirection(Servo.Direction.REVERSE);
                leftClaw.setPosition(-.9 );
                //gamepad2.left_trigger = 0;
                //gamepad1.left_trigger = 0;
            } 
            
            
            if(gamepad2.right_bumper || gamepad1.right_bumper){
                rightClaw.setDirection(Servo.Direction.FORWARD);
                rightClaw.setPosition(1);
                
            } else if(gamepad2.right_trigger!= 0 || gamepad1.right_trigger!= 0){
                rightClaw.setDirection(Servo.Direction.REVERSE);
                rightClaw.setPosition(.65);
                //gamepad2.right_trigger = 0;
                //gamepad1.right_trigger = 0;
            } 
            
            
            if (gamepad2.b || gamepad1.b){
                trigger.setPosition(trigger.getPosition()+.5);
            }
                
            // Choose to drive using either Tank Mode, or POV Mode
            // Comment out the method that's not used.  The default below is POV.
            
            // POV Mode uses left stick to go forward, and right stick to turn.
            
            while (armHeight.getCurrentPosition() != 0 && false){
                if (armHeight.getCurrentPosition() < 0){
                    moveArm(200, .5);
                }
                if (armHeight.getCurrentPosition() > 0){
                    moveArm(-200 , .5);
                }
            };
            // - This uses basic math to combine motions and is easier to drive straight.
            double drive = -gamepad2.left_stick_y;
            double turn  =  gamepad2.right_stick_x;
            leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
            rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;

            
           
            // Tank Mode uses one stick to control each wheel.
            // - This requires no math, but it is hard to drive forward slowly and keep straight.
            // leftPower  = -gamepad1.left_stick_y ;
            // rightPower = -gamepad1.right_stick_y ;

            // Send calculated power to wheels
            leftDrive.setPower(leftPower);
            rightDrive.setPower(rightPower);

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.addData("servohd",rightClaw.getPosition());
            telemetry.addData("truggeer engaged",gamepad1);
            telemetry.addData("arhmdu:", armHeight.getCurrentPosition());
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
            }
         private void lockArm(int lockPosition , double power ){

        while (armHeight.getCurrentPosition() != lockPosition){
            if (armHeight.getCurrentPosition() - lockPosition >= 350){
                moveArm(-200, .5);
            }
            if (lockPosition - armHeight.getCurrentPosition() >=350){
                moveArm(200 , .5);

            }
        }

    }
}





