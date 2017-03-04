 <?php
 $con = mysqli_connect('localhost','root','Tombert1', 'login') or die("Error");
 if(isset($_POST['Login'])){
     $username = mysqli_real_escape_string($con, $_POST['username']);
     $password = mysqli_real_escape_string($con, $_POST['password']);

     $sel_user = "select * from userinfo where firstName='$username' AND lastName='$password'";

     $run_user = mysqli_query($con, $sel_user);

     $row = $run_user->fetch_assoc();

     $check_user = mysqli_num_rows($run_user);

     if($check_user>0){

        if ($row["userType"] == "Admin"){
            $link = "<script>window.open('http://localhost:8080/AppController.java/new','_self')</script>";

            echo $link;
        }
        else if ($row["userType"] == "User"){
            $link = "<script>window.open('HelloUser.html','_self')</script>";

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
     <input type="password" placeholder="Password" name="password"><br><br>
     <input type="submit" name="Login" value="Login"/><br><br>
     <input type="button" onclick="check(this.form)" value="Create Account"/>
 </form>

<script language="javascript">
function check(form)
{
     window.open('http://localhost:8080/register.jsp','_self');
}
 </script>
 </body>
 </html>