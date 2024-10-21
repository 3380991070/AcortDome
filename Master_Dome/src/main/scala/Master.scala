import akka.actor.{Actor, ActorRef, ActorSystem}
import akka.dispatch.ExecutionContexts.global

import java.text.SimpleDateFormat
import java.util.Date
import scala.collection.mutable
import scala.collection.mutable._
import scala.concurrent.duration.DurationInt
import java.time._

object Master extends Actor
{
    var WorkerMap : mutable.HashMap[String, WorkerInfo] = new HashMap[String,WorkerInfo]();

    override def preStart()={
        //context.system.actorSelection("akka://MasterSystem/user/Master")
        context.system.scheduler.scheduleAtFixedRate(0.seconds,ConfigUilt.interval._1.seconds,MasterMain.master,"心跳")(global, Actor.noSender);
    }

    override def receive: Receive =
    {
        //接收注册信息
        case MessgPackage.WorkerMessgPackage(id,cpu,mem) =>{
            println(s"接收到Worker注册信息${id} + ${cpu} + ${mem}")
            WorkerMap += id -> WorkerInfo(id,cpu,mem,new Date().getTime)
            sender ! MessgPackage.MessgSuccessPackage;
            println(WorkerMap);
        }
        case MessgPackage.WorkerHeart(id,cpu,mem) =>{
            WorkerMap(id) = WorkerInfo(id,cpu,mem,new Date().getTime)
            println(s"收到心跳消息:${sender}");
        }
        case "心跳" =>{
            if(WorkerMap.size != 0){
                for((k,v) <- WorkerMap){
//                    var e = new LocalDateTime();
                    if((new Date().getTime) - WorkerMap(k).HearTime > (ConfigUilt.interval._2 * 1000)){
                        println((new Date().getTime) - WorkerMap(k).HearTime + "," + (ConfigUilt.interval._2 * 1000))
                        println(s"死亡Worker : ${WorkerMap(k).workerid}");
                        WorkerMap -= k;
                    }
                }
                //返回Iterable特质
                var inte = WorkerMap.values;
                //排序
                inte = inte.toList.sortWith((l1 : WorkerInfo,l2:WorkerInfo) =>{
                    l1.mem > l2.mem
                })
                //输出
                inte.foreach((f : WorkerInfo) =>
                {
                    println(f + "当前长度 : "  + WorkerMap.size)
                });
            }
        }
    }
}
