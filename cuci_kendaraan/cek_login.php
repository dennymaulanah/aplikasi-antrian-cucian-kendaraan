<?php
session_start();
require 'koneksi.php';
$login=mysqli_query($con,"select * from tb_admin where
  username='$_POST[username]' and password='$_POST[password]'");

  $dapat=mysqli_num_rows($login);
  $r=mysqli_fetch_array($login);
  //apabila username dan password ditemukan
  if($dapat > 0)
  {
     session_start(); //awal session
    
    //isi dari variabel session
     $_SESSION['username']=$r['username'];
     $_SESSION['username']=$r['password'];
    //buka halaman utama administrator
     header('location:index.php');
  } 
else
{
   print "<script>
   alert(\"Periksa Pengisian Form\");
   location.href = \"login.php\";
   </script>";
}
?>