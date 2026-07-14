import { apiClient } from "../../../api/axios";

import type { EncryptRequest } from "../types/EncryptRequest";
import type { EncryptResponse } from "../types/EncryptResponse";

export async function encryptMessage(request: EncryptRequest): Promise<EncryptResponse> {

    const response = await apiClient.post<EncryptResponse>("/encrypt", request);

    return response.data;
}