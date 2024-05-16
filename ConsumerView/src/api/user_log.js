import request from "@/utils/jwt";

export const getBrowseLog = (username) => {
    return request.get("/log/list", {
        params: {action: "BROWSE", username}
    })
}

export const getPurchaseLog = (username) => {
    return request.get("/log/list", {
        params: {action: "PURCHASE", username}
    })
}

export const addBrowseLog = (data) => {
    return request.post("/log/add", data)
}

export const addPurchaseLog = (data) => {
    return request.post("/log/add", data)
}