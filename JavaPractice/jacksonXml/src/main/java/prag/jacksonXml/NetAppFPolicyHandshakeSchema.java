package prag.jacksonXml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

public class NetAppFPolicyHandshakeSchema {

    /* package */ static class ProtocolVersion {
        @JacksonXmlProperty(localName = "Vers")
        public String vers;
    }

    @JacksonXmlRootElement(localName = "Handshake")
    /* package */ static class HandshakeReq {
        @JacksonXmlProperty(localName = "VsUUID")
        public String vsUUID;

        @JacksonXmlProperty(localName = "PolicyName")
        public String policyName;

        @JacksonXmlProperty(localName = "SessionId")
        public String sessionId;

        @JacksonXmlProperty(localName = "ProtVersion")
        @JacksonXmlElementWrapper(useWrapping = false)
        public List<ProtocolVersion> protVersion;

        public HandshakeReq() { }

        public HandshakeReq(String vsUUID, String policyName, String sessionId, List<ProtocolVersion> protVersion) {
            this.vsUUID = vsUUID;
            this.policyName = policyName;
            this.sessionId = sessionId;
            this.protVersion = protVersion;
        }

        @Override
        public String toString() {
            return new StringBuilder().append(vsUUID).append(", ").append(policyName).append(", ")
                                      .append(sessionId).append(", ").append(protVersion).toString();
        }

        public String getLatestProtVersion() {
            String latestProtVersion = "";
            for(ProtocolVersion entry : protVersion) {
                if (entry.vers.compareTo(latestProtVersion) >= 1)
                {
                    latestProtVersion = entry.vers;
                }
            }
            return latestProtVersion;
        }
    }

    @JacksonXmlRootElement(localName = "HandshakeResp")
    /* package */ static class HandshakeResp {
        @JacksonXmlProperty(localName = "VsUUID")
        public String vsUUID;

        @JacksonXmlProperty(localName = "PolicyName")
        public String policyName;

        @JacksonXmlProperty(localName = "SessionId")
        public String sessionId;

        @JacksonXmlProperty(localName = "ProtVersion")
        public String protVersion;

        public HandshakeResp() { }

        public HandshakeResp(String vsUUID, String policyName, String sessionId, String protVersion) {
            this.vsUUID = vsUUID;
            this.policyName = policyName;
            this.sessionId = sessionId;
            this.protVersion = protVersion;
        }

        @Override
        public String toString() {
            return new StringBuilder().append(vsUUID).append(", ").append(policyName).append( ", ")
                                      .append(sessionId).append(", ").append(protVersion).toString();
        }
    }
}
