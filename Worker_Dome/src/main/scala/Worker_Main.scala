import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory

import java.io.File

object Worker_Main
{
    //akka://ActorSystem@127.0.0.1:7668
    def main(args: Array[String]): Unit =
    {
        //创建ActorSystem用来管理所有自定义Actor
        val ActorSystemSystem = ActorSystem.create("ActorSystem", ConfigFactory.load());
        var worker = ActorSystemSystem.actorOf(Props(Worker),"Worker");
    }
}
