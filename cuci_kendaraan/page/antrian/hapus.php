<?php

	$id = $_GET['id'];

	$con->query("delete from tb_antrian where id = '$id' ");

?>

<script type="text/javascript">
	window.location.href="?page=antrian";
</script>