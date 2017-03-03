 <?php
 $con = mysqli_connect('localhost','root','Tombert1', 'login') or die("Error");
 if(isset($_POST['Login'])){
     $username = mysqli_real_escape_string($con, $_POST['username']);
     $password = mysqli_real_escape_string($con, $_POST['password']);
     $userType = "";

     $sel_user = "select * from userinfo where firstName='$username' AND lastName='$password'";

     $sel_type = "select * from userinfo where userType='$userType'";

     $run_user = mysqli_query($con, $sel_user);

     $check_user = mysqli_num_rows($run_user);

     if($check_user>0){

        if (strcmp($userType,'Admin') == 0){
            $link = "<script>window.open('AdminLogin.html','_self')</script>";

            echo $link;
        }
        else if (strcmp($userType,'User') == 0){
            $link = "<script>alert(User!')</script>";

            echo $link;
        }

     }
     else{
        $link = "<script>alert('Username or password incorrect, try again!')</script>";

        echo $link;
     }
 }
 ?>



 <html lang="en">
 <head>
     <meta charset="UTF-8">
     <title>Login Page</title>

 </head>
 <body>

 <form action = "LoginPage.php" method="post">
     <input type="text" placeholder="Username" name="username"><br><br>
     <input type="text" placeholder="Password" name="password"><br><br>
     <input type="submit" name="Login" value="Login"/>
 </form>
 </body>
 </html>