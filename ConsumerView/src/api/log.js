import request from "@/utils/jwt";

export const getBrowseLog = (username) => {
    return request.get("/log/browse/list", {
        params: {username}
    })
}

export const addBrowseLog = (data) => {
    return request.post("/log/browse/add", data)
}

export const getOperateLog = () => {
    return request.get("/log/operate/list")
}

export const deleteBrowseLog = (id) => {
    return request.get('/log/operate/delete', {
        params: {id}
    })
}