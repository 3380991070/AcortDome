object MessgPackage
{
    /** *
     * 用来给Worker的注册信息包
     *
     * @param workid Worker的ID
     * @param cpu    Worker的CPU核心数量
     * @param mem    Worker的内存大小
     */
    case class WorkerMessgPackage(workid: String, cpu: Int, mem: Int)

    /** *
     * 用来给Master给Worker发送注册成功的消息
     */
    case object MessgSuccessPackage

    /** *
     * Worker的心跳消息包
     *
     * @param workid Worker的ID
     * @param cpu    Worker的CPU核心数
     * @param mem    Worker的内存大小
     */
    case class WorkerHeart(workid: String, cpu: Int, mem: Int)
}