<?php  
$serverName = "140.131.114.241"; //serverName\instanceName
$connectionInfo = array( "Database"=>"108-plantfun", "UID"=>"ntub108203", "PWD"=>"@Ntub108203", "CharacterSet" => "UTF-8");
$con = sqlsrv_connect( $serverName, $connectionInfo);
 $json='';
 $data=array();
 class User //User類別，內中有許多變數(會對應資料庫中的各項項目)
 {
     public $cname;
     public $sname;
     public $place;
     public $life;
     public $branch;
     public $leaf;
     public $spore;
     public $fert;
     public $use;
 }

$number = $_GET['number'];//android將會傳值到number


  $sql = "select * from 蕨類 where SID = ".$number;//從資料庫挑選對應的ID資料
  $result=sqlsrv_query($con,$sql);//user資料表名稱
  if($result){ //連接上時
  while($row = sqlsrv_fetch_array($result))//當抓取到資料庫中資料時所作的設定
  {
    $user = new User();//建立新群組
    $user->cname = $row["cname"];//$user是android要讀取的變數
    $user->sname = $row["sname"];//以下每行分別對應上方User類別中的變數，中文字都是資料庫中的各個項目
    $user->place = $row["place"];
    $user->life = $row["life"];
    $user->branch = $row["branch"];
    $user->leaf = $row["leaf"];
    $user->spore = $row["spore"];
    $user->fert = $row["fert"];
    $user->use = $row["describe"];

  }
 $json = json_encode($user);//將data矩陣資料轉換為JSON資料.
 echo $json;
}
?> 
