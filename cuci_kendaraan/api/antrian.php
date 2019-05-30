<?php 
	include "../koneksi.php";
	
	$query = mysqli_query($con, "SELECT * FROM tb_antrian WHERE status = '1' || status = '2'|| status = '3' ORDER BY id DESC");
	
	$json = array();
	
	while($row = mysqli_fetch_assoc($query)){
		$json[] = $row;
	}
	
	echo json_encode($json);
	
	mysqli_close($con);
	
?>