import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory

object MasterMain
{
    //akka://MasterSystem@127.0.0.1:7666
    def main(args: Array[String]): Unit =
    {
        //创建ActorSystem用来管理所有自定义Actor
        var MasterSystem = ActorSystem.create("MasterSystem", ConfigFactory.load());
        //通过MasterSystem管理Master
        var master = MasterSystem.actorOf(Props(Master),"Master");
    }
}
