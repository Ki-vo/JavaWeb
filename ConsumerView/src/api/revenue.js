import request from "@/utils/jwt";

export const getTotalRevenueService = (data) => {
    return request.get('/revenue/total');
}