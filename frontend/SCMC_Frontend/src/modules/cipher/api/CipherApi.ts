import { apiClient } from "../../../api/axios";
import type { DecryptRequest } from "../types/DecryptRequest";
import type { DecryptResponse } from "../types/DecryptResponse";

import type { EncryptRequest } from "../types/EncryptRequest";
import type { EncryptResponse } from "../types/EncryptResponse";

export async function encryptMessage(request: EncryptRequest): Promise<EncryptResponse> {

    const response = await apiClient.post<EncryptResponse>("api/encrypt", request);

    return response.data;
}

export async function decryptMessage(request: DecryptRequest): Promise<DecryptResponse> {

    const response = await apiClient.post<DecryptResponse>("api/decrypt", request);
    return response.data;
}