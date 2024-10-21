import com.typesafe.config.ConfigFactory

object ConfigUilt
{
    //master.check.heartbeat.interval = 6
    //master.check.heartbeat.timeout = 15
    private var config = ConfigFactory.load();

    /**
     *
     * @return 1=>检测时间 2=>超时时间
     */
    def interval = {
          (config.getInt("master.check.heartbeat.interval"),config.getInt("master.check.heartbeat.timeout"));
    }
}
