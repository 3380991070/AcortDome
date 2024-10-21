/***
 * Worker的信息
 * @param workerid ID
 * @param cpu   CPU数量
 * @param mem   内存大小
 * @param HearTime  心跳时间
 */
case class WorkerInfo(var workerid : String,var cpu:Int,var mem:Int,var HearTime:Long)
