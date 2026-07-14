import * as EncryptApi from "../api/EncryptApi";

import type { EncryptRequest } from "../types/EncryptRequest";
import type { EncryptResponse } from "../types/EncryptResponse";

export async function encrypt(request: EncryptRequest): Promise<EncryptResponse> {

    return await EncryptApi.encryptMessage(request);

}