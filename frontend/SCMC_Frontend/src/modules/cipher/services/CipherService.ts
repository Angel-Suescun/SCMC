import * as EncryptApi from "../api/CipherApi";

import type { DecryptResponse } from "../types/DecryptResponse";
import type { DecryptRequest } from "../types/DecryptRequest";
import type { EncryptRequest } from "../types/EncryptRequest";
import type { EncryptResponse } from "../types/EncryptResponse";

export async function encrypt(request: EncryptRequest): Promise<EncryptResponse> {

    return await EncryptApi.encryptMessage(request);

}

export async function decrypt(request: DecryptRequest): Promise<DecryptResponse> {

    return await EncryptApi.decryptMessage(request);

}