<?php

	include_once "../koneksi.php";

	 class usr{}
	
	$token = $_POST["token"];
	
	if ((empty($token))) { 
		$response = new usr();
		$response->success = 0;
		$response->message = "Kolom tidak boleh kosong"; 
		die(json_encode($response));
	}
	
	$num_rows = mysqli_num_rows(mysqli_query($con, "SELECT * FROM tb_antrian WHERE token='".$token."'"));

	$query = mysqli_query($con, "SELECT * FROM tb_antrian WHERE token='$token'");

	$row = mysqli_fetch_array($query);

	if ($num_rows > 0) {
		
			$response = new usr();
		 	$response->success = 1;
		 	$response->message = "Selamat datang ".$row['pemilik'];
		 	$response->id = $row['id'];
		 	$response->token = $row['token'];
		 	$response->pemilik = $row['pemilik'];
		 	$response->status = $row['status'];
		 	$response->no_kendaraan = $row['no_kendaraan'];
		 	$response->kendaraan = $row['kendaraan'];
		 	$response->tipe_kendaraan = $row['tipe_kendaraan'];
		 	$response->waktu_keluar = $row['waktu_keluar'];
		 	
		 	
		 	
		 	die(json_encode($response));


	}else{

		$response = new usr();
	 	$response->success = 0;
	 	$response->message = "Token salah";
	 	die(json_encode($response));
	 
	}
	 mysqli_close($con);

?>