                    <div class="panel panel-default">
                    	<div class="panel-heading">
                    		Tambah Antrian
                    	</div>
                    	<div class="panel-body">
                    		<div class="row">
                    			<div class="col-md-12">
                    				<form method="POST">
                    					<div class="form-group">
                    						<label>Pemilik</label>
                    						<input class="form-control" name="pemilik" />
                    					</div>
                    					<div class="form-group">
                    						<label>No Kendaraan</label>
                    						<input class="form-control" name="no_kendaraan" />
                    					</div>
                                        <div class="form-group">
                                            <label>Kendaraan</label>
                                          <select class="form-control" name="kendaraan">
                                               <option value="1">Motor</option>
                                               <option value="2">Mobil</option>
                                          </select>
                                        </div>
                    					<div class="form-group">
                    						<label>Tipe Kendaraan</label>
                    						<input class="form-control" name="tipe_kendaraan" />
                    					</div>
                    					<div>
                    						<input type="submit" name="simpan" value="Simpan" class="btn btn-primary">
                    					</div>
                    				</div>
                    			</form>
                    		</div>
                    	</div>
                    </div>
                </div>
                <?php
                $pemilik = $_POST['pemilik'];
                $no_kendaraan = $_POST['no_kendaraan'];
                $tipe_kendaraan = $_POST['tipe_kendaraan'];
                $kendaraan = $_POST['kendaraan'];
                $str = 'qawsedrftgyhujikolpzxcvbnm=-0987654321ZAXSCDVFBGNHMJKL/QWERTYUIOP[];\/+_?><';
                $str = str_shuffle($str);
                $token = substr($str, 0 , 10);
                $status = 1;

                $simpan = $_POST['simpan'];

                if ($simpan) {
                    if (empty($pemilik) || empty($no_kendaraan) || empty($kendaraan)) {
                                                ?>
                        <script type="text/javascript">

                            alert ("Kolom tidak boleh kosong");
                            window.location.href="?page=antrian&aksi=tambah";

                        </script>
                        <?php

                    }else{
                	$sql = $con->query("insert into tb_antrian (pemilik, no_kendaraan, tipe_kendaraan, kendaraan, token, waktu_antri, status) values( '$pemilik', '$no_kendaraan', '$tipe_kendaraan', '$kendaraan', '$token', CURRENT_TIMESTAMP , '$status')");
                	if ($sql) {
                		?>
                		<script type="text/javascript">

                			alert ("Data Berhasil Disimpan");
                			window.location.href="?page=antrian";

                		</script>
                		<?php
                	}
                }
            }
                ?>