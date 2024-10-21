
import com.typesafe.config.ConfigFactory

import java.io.{File, FileInputStream}
import scala.io.Source
import scala.util.control.Breaks

object ConfigUtils
{
    private val config = ConfigFactory.load();
    var `worker.heartbeat.interval` : Int = config.getInt("worker.heartbeat.interval");
//    /**
//     *
//     * @param file 文件路径
//     * @return 返回-1无 否则返回值
//     */
//    def FileConfigHeart(file : File):Int ={
//        //读取文件
//        var source = Source.fromFile(file);
//        //获取字段 行读取
//        var stringInter = source.getLines();
//        while (stringInter.hasNext)
//        {
//            var str = stringInter.next();
//            //判断配置行
//            if(str.startsWith("worker.heartbeat.interval")){
//                //最后一个字符为配置值
//                val strings = str.split("=");
//                //stringsub也可以截取字符
//                return strings(1).trim.toInt;
//            }
//        }
//        -1
//    }
}
