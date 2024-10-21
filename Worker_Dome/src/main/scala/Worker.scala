import akka.actor.{Actor, ActorSelection}

import java.lang.invoke.MethodHandles.loop
import java.util.UUID

import scala.util.Random

object Worker extends Actor
{
    //步骤
    //1.定义成员变量
    /**
     * @param MasterActorRef -> 对于主Master
     * @param WorkerID       -> Worker的ID
     * @param cpu            -> CPU的核心数
     * @param mem            -> 内存的大小
     * @param cpuList        -> CPU核心数的定义
     * @param memList        -> 内存大小的定义
     */
    private var MasterActorRef: ActorSelection = _;//context.actorSelection("akka.tcp://MasterSystem@127.0.0.1:7666/user/Master");
    private var WorkerID : String = _;
    private var cpu : Int = _;
    private var mem : Int = _;
    private val cpulist : List[Int] = List(1,2,3,4,6,8);
    private var memlist : List[Int] = List(512,1024,1536,2048);
    override def preStart() = {
        MasterActorRef = context.system.actorSelection("akka://MasterSystem@127.0.0.1:7666/user/Master");
        WorkerID = UUID.randomUUID().toString;
        cpu = cpulist(Random.nextInt(cpulist.length));
        mem = memlist(Random.nextInt(memlist.length));
        //创建注册信息
        var WorkerInfo = MessgPackage.WorkerMessgPackage(WorkerID,cpu,mem);
        //发送注册信息
        MasterActorRef ! WorkerInfo;
    }

//    private var hand = false;
    override def receive: Receive =
    {
        case MessgPackage.MessgSuccessPackage =>{
            println("Worker已经注册成功");

            import scala.concurrent.duration._
            import scala.language.postfixOps
            import scala.concurrent.ExecutionContext.Implicits.global

            context.system.scheduler.scheduleAtFixedRate(
                initialDelay = 0.seconds,
                interval = ConfigUtils.`worker.heartbeat.interval`.seconds,
                receiver = sender,
                message = MessgPackage.WorkerHeart(WorkerID, cpu, mem)
            )(global, Actor.noSender)
        }
    }
}
