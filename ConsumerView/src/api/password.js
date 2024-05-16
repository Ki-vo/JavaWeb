import request from "@/utils/jwt";

export const editPasswordService = (requestData) => {
    return request.post("/password/edit", requestData)
}