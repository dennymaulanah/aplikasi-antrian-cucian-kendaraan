<div class="row">
                <div class="col-md-12">
                    <!-- Advanced Tables -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                             <h1>Riwayat Antrian</h1>
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <thead>
                                        <tr>
                                            <th>No</th>
                                            <th>Pemilik</th>
                                            <th>No Kendaraan</th>
                                            <th>Kendaraan</th>
                                            <th>Tipe Kendaraan</th>
                                            <th>Token</th>
                                            <th>Waktu Antri</th>
                                            <th>Status</th>
                                            <th>Aksi</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<?php

                                    		$no=1;
                                    		$sql = $con->query("select *from tb_antrian where status = 3 || status = 4");
                                    		while ($data = $sql->fetch_assoc()){
                                    	?>
                                    	<tr>
                                    		<td><?php echo $no++?></td>
                                    		<td><?php echo $data['pemilik']?></td>
                                    		<td><?php echo $data['no_kendaraan']?></td>
                                    		<?php 
                                            if($data['kendaraan'] == 1){
                                                ?><td><?php echo "Motor"?></td>
                                                <?php
                                            }else if($data['kendaraan'] == 2){
                                                ?><td><?php echo "Mobil"?></td>
                                                <?php
                                            }

                                            ?>
                                            <td><?php echo $data['tipe_kendaraan']?></td>
                                            <td><?php echo $data['token']?></td>
                                            <td><?php echo date($data['waktu_antri'])?></td>
                                            <?php 
                                            if($data['status'] == 3){
                                                ?><td><?php echo "Selesai"?></td>
                                                <?php
                                            }elseif($data['status'] == 4){
                                                ?><td><?php echo "Sudah diambil"?></td>
                                                <?php
                                            }
                                            ?>
                                    		<td>
                                    			<a href="?page=riwayat&aksi=ubah&id=<?php echo $data['id']?>" class="btn btn-info">Ubah</a>
                                    			<a onclick="return confirm('Hapus Data ?')" href="?page=riwayat&aksi=hapus&id=<?php echo $data['id']?>" class="btn btn-danger">Hapus</a>
                                    		</td>
                                    	</tr>
                                    <?php }?>
                                    </tbody>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>