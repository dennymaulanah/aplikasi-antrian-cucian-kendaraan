                    <?php

                    $id =$_GET['id'];

                    $sql = $con->query("select *from tb_antrian where id ='$id' ");

                    $tampil = $sql->fetch_assoc();

                    $status = $tampil['status'];
                    $kendaraan = $tampil['kendaraan'];

                    ?>
                    <div class="panel panel-default">
                      <div class="panel-heading">
                        Ubah Data Buku
                      </div>
                      <div class="panel-body">
                        <div class="row">
                          <div class="col-md-12">
                            <form method="POST">
                              <div class="form-group">
                                <label>Pemilik</label>
                                <input class="form-control" name="pemilik" value="<?php echo $tampil['pemilik']; ?>" />
                              </div>
                              <div class="form-group">
                                <label>No Kendaraan</label>
                                <input class="form-control" name="no_kendaraan" value="<?php echo $tampil['no_kendaraan']; ?>" />
                              </div>
                                        <div class="form-group">
                                                  <label>Kendaraan</label>
                                                  <select class="form-control" name="kendaraan">
                                                       <option value="1" <?php if ($kendaraan=='1'){echo "selected";} ?> >Motor</option>
                                                       <option value="2" <?php if ($kendaraan=='2'){echo "selected";} ?> >Mobil</option>
                                                  </select>
                                             </div>
                              <div class="form-group">
                                <label>Tipe Kendaraan</label>
                                <input class="form-control" name="tipe_kendaraan" value="<?php echo $tampil['tipe_kendaraan']; ?>" />
                              </div>
                                             <div class="form-group">
                                                  <label>Status</label>
                                                  <select class="form-control" name="status">
                                                       <option value="1" <?php if ($status=='1'){echo "selected";} ?> >Menunggu</option>
                                                       <option value="2" <?php if ($status=='2'){echo "selected";} ?> >Proses</option>
                                                       <option value="3" <?php if ($status=='3'){echo "selected";} ?> >Selesai</option>
                                                       <option value="4" <?php if ($status=='4'){echo "selected";} ?> >Sudah diambil</option>
                                                  </select>
                                             </div>
                                <div>
                                  <input type="submit" name="simpan" value="Ubah" class="btn btn-primary">
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
                    $kendaraan = $_POST['kendaraan'];
                    $tipe_kendaraan = $_POST['tipe_kendaraan'];
                    $status = $_POST['status'];
                

                     $simpan = $_POST['simpan'];

                     if ($simpan) {
                         if (empty($pemilik) || empty($no_kendaraan) || empty($kendaraan)|| empty($tipe_kendaraan)) {
                                                     ?>
                             <script type="text/javascript">

                                 alert ("Kolom tidak boleh kosong");
                                 window.location.href="?page=riwayat&aksi=ubah";

                             </script>
                             <?php

                         }else{
                         $sql = $con->query("update tb_antrian set pemilik='$pemilik', no_kendaraan='$no_kendaraan', kendaraan='$kendaraan', tipe_kendaraan='$tipe_kendaraan', status='$status', waktu_keluar=CURRENT_TIMESTAMP where id ='$id'");
                         if ($sql) {
                              ?>
                              <script type="text/javascript">

                                   alert ("Data Berhasil Diubah");
                                   window.location.href="?page=riwayat";

                              </script>
                              <?php
                         }
                     }
                 }
                     ?>