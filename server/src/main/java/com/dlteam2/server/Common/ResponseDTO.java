package com.dlteam2.server.Common;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;

import java.util.List;

public class ResponseDTO {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Response{
        private int code;
        private String message;

        @Builder
        public Response(int code, String message) {
            this.code = code;
            this.message = message;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class DataResponse{
        private int code;
        private String message;
        private JSONObject data;

        @Builder
        public DataResponse(int code, String message, JSONObject data) {
            this.code = code;
            this.message = message;
            this.data = data;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ListResponse{
        private int code;
        private String message;
        private List data;

        @Builder
        public ListResponse(int code, String message, List data) {
            this.code = code;
            this.message = message;
            this.data = data;
        }
    }

}
