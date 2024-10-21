import akka.actor.{Actor, ActorRef}

import java.util.Date
import scala.collection.mutable
import scala.collection.mutable._

object Master extends Actor
{
    var WorkerMap : mutable.HashMap[ActorRef, WorkerInfo] = new HashMap[ActorRef,WorkerInfo]();

    override def receive: Receive =
    {
        //接收注册信息
        case MessgPackage.WorkerMessgPackage(id,cpu,mem) =>{
            println(s"接收到Worker注册信息${id} + ${cpu} + ${mem}")
            WorkerMap += sender -> WorkerInfo(id,cpu,mem,new Date().getTime)
            sender ! MessgPackage.MessgSuccessPackage;
            println(WorkerMap);
        }
    }
}
