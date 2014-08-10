package com.vijayrc.storm.append;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import com.vijayrc.storm.MyTopology;

import static backtype.storm.utils.Utils.sleep;

public class AppendTopology implements MyTopology{
    private String name = "append";

    @Override
    public void run() {
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("s1", new WordSpout("s1"), 2);
        builder.setBolt("b1",new AppendBolt("b1"),3).shuffleGrouping("s1");
        builder.setBolt("b2",new AppendBolt("b2"),1).shuffleGrouping("b1");

        Config config = new Config();
        config.setDebug(false);

        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology(name,config,builder.createTopology());
        sleep(10000);
        cluster.killTopology(name);
        cluster.shutdown();
    }

    @Override
    public String name() {
        return name;
    }
}