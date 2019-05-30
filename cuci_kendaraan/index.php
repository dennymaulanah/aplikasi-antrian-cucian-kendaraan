<?php
session_start();
//apabiila user yang mengakses tidak sah
if (empty ($_SESSION['username']) and empty ($_SESSION['password'])) {
    echo "<center>Untuk megakses halaman ini, anda harus login terlebih dahulu <br>";
    echo "<a href=login.php><b>LOGIN</b></a></center>";
}

//apabila user yang mengakses sah
else {

?>
<?php include "koneksi.php" ?>
<?php error_reporting(E_ALL^(E_NOTICE |E_WARNING));?>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Administrator</title>
  <!-- BOOTSTRAP STYLES-->
  <link href="assets/css/bootstrap.css" rel="stylesheet" />
  <!-- FONTAWESOME STYLES-->
  <link href="assets/css/font-awesome.css" rel="stylesheet" />
  <!-- CUSTOM STYLES-->
  <link href="assets/css/custom.css" rel="stylesheet" />
  <!-- GOOGLE FONTS-->
  <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
  <!-- TABLE STYLES-->
  <link href="assets/js/dataTables/dataTables.bootstrap.css" rel="stylesheet" />
</head>
<body>
    <div id="wrapper">
        <nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.php">Administrator</a> 
            </div>
            <div style="color: white;
            padding: 15px 50px 5px 50px;
            float: right;
            font-size: 16px;"> Tanggal : <?php echo date('d F Y')?> &nbsp; <a href="logout.php" class="btn btn-danger square-btn-adjust">Logout</a> </div>
        </nav>   
        <!-- /. NAV TOP  -->
        <nav class="navbar-default navbar-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="main-menu">
                    <li class="text-center">
                        <img src="assets/img/find_user.png" class="user-image img-responsive"/>
                    </li>

                    
                    <li>
                        <a href="?page=antrian"><i class="fa fa-dashboard fa-3x"></i> Antrian</a>
                    </li>
                    <li>
                        <a href="?page=riwayat"><i class="fa fa-dashboard fa-3x"></i> Riwayat</a>
                    </li>
                </ul>

            </div>
            
        </nav>  
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper" >
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                        <?php

                        $page = $_GET['page'];
                        $aksi = $_GET['aksi'];

                            //Anggota
                        if($page == "antrian"){
                            if($aksi == ""){
                                include "page/antrian/antrian.php";
                            }
                            else if ($aksi == "tambah") {
                                include "page/antrian/tambah.php";
                            }
                            else if ($aksi == "ubah") {
                                include "page/antrian/ubah.php";
                            }
                            else if ($aksi == "hapus") {
                                include "page/antrian/hapus.php";
                            }
                            //Buku
                        }else if($page == "riwayat"){
                            if($aksi == ""){
                                include "page/riwayat/riwayat.php";
                            }
                            else if ($aksi == "ubah") {
                                include "page/riwayat/ubah.php";
                            }
                            else if ($aksi == "hapus") {
                                include "page/riwayat/hapus.php";
                            }
                        }else{
                            echo "Selamat Datang";
                        }
                        ?>
                    </div>
                </div>
                <!-- /. ROW  -->
                <hr />

            </div>
            <!-- /. PAGE INNER  -->
        </div>
        <!-- /. PAGE WRAPPER  -->
    </div>
    <!-- /. WRAPPER  -->
    <!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
    <!-- JQUERY SCRIPTS -->
    <script src="assets/js/jquery-1.10.2.js"></script>
    <!-- BOOTSTRAP SCRIPTS -->
    <script src="assets/js/bootstrap.min.js"></script>
    <!-- METISMENU SCRIPTS -->
    <script src="assets/js/jquery.metisMenu.js"></script>
    <!-- DATA TABLE SCRIPTS -->
    <script src="assets/js/dataTables/jquery.dataTables.js"></script>
    <script src="assets/js/dataTables/dataTables.bootstrap.js"></script>
    <script>
        $(document).ready(function () {
            $('#dataTables-example').dataTable();
        });
    </script>
    <!-- CUSTOM SCRIPTS -->
    <script src="assets/js/custom.js"></script>
    
    

</body>
</html>
<?php
}
?>