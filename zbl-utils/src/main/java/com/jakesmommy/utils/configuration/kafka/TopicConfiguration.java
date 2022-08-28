package com.jakesmommy.utils.configuration.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "kafkas", ignoreUnknownFields = false)
public class TopicConfiguration {

    public TopicConfiguration() {

    }

    private List<TopicConfigModel> topics;

    public List<TopicConfigModel> getTopics() {
        return topics;
    }

    public void setTopics(List<TopicConfigModel> topics) {
        this.topics = topics;
    }

    @Component
    public static class TopicConfigModel {


        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private Integer numpartitions;
        private Short replicafactor;

        public Integer getNumpartitions() {
            return numpartitions;
        }

        public void setNumpartitions(Integer numpartitions) {
            this.numpartitions = numpartitions;
        }

        public Short getReplicafactor() {
            return replicafactor;
        }

        public void setReplicafactor(Short replicafactor) {
            this.replicafactor = replicafactor;
        }

        public NewTopic topic() {
            return new NewTopic(this.getName(), this.getNumpartitions(), this.getReplicafactor());
        }
    }

}
