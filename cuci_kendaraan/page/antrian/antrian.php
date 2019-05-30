<div class="row">
                <div class="col-md-12">
                    <!-- Advanced Tables -->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                             <h1>Data Antrian</h1>
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
                                            <th>Status</th>
                                            <th>Aksi</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<?php

                                    		$no=1;
                                    		$sql = $con->query("select *from tb_antrian where status = 1 || status = 2 order by status asc");
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
                                            <?php 
                                            if($data['status'] == 1){
                                                ?><td><?php echo "Menunggu"?></td>
                                                <?php
                                            }else if($data['status'] == 2){
                                                ?><td><?php echo "Proses"?></td>
                                                <?php
                                            }else if($data['status'] == 3){
                                                ?><td><?php echo "Selesai"?></td>
                                                <?php
                                            }else if($data['status'] == 4){
                                                ?><td><?php echo "Diambil"?></td>
                                                <?php
                                            }

                                            ?>
                                    		<td>
                                    			<a href="?page=antrian&aksi=ubah&id=<?php echo $data['id']?>" class="btn btn-info">Ubah</a>
                                    			<a onclick="return confirm('Hapus Data ?')" href="?page=antrian&aksi=hapus&id=<?php echo $data['id']?>" class="btn btn-danger">Hapus</a>
                                    		</td>
                                    	</tr>
                                    <?php }?>
                                    </tbody>
                                    <a href="?page=antrian&aksi=tambah" class="btn btn-primary">Tambah Antrian</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>