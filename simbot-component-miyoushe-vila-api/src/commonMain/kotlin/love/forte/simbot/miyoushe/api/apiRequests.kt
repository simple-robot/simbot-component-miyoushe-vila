/*
 * Copyright (c) 2023. ForteScarlet.
 *
 * This file is part of simbot-component-miyoushe.
 *
 * simbot-component-miyoushe is free software: you can redistribute it and/or modify it under the terms of
 * the GNU Lesser General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * simbot-component-miyoushe is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with simbot-component-miyoushe,
 * If not, see <https://www.gnu.org/licenses/>.
 */

package love.forte.simbot.miyoushe.api

import io.ktor.client.*
import io.ktor.client.statement.*
import kotlinx.serialization.json.Json
import love.forte.simbot.miyoushe.MiyousheVilla
import kotlin.jvm.JvmSynthetic

@JvmSynthetic
public suspend fun <R : Any> MiyousheVillaApi<R>.requestResult(
    client: HttpClient,
    token: MiyousheVillaApiToken,
    decoder: Json = MiyousheVilla.DefaultJson
): ApiResult<R> {
    val response = request(client, token)
    val text = response.bodyAsText()
    return decoder.decodeFromString(apiResultSerializer, text)
}

/**
 * 请求目标API并将结果解析为预期类型。
 * 如果响应结果代表了失败，则会抛出 [ApiResultNotSuccessException]。
 *
 * @receiver 需要请求的 API
 * @see ApiResult.dataIfSuccess
 * @throws ApiResultNotSuccessException 如果结果不是成功
 */
@JvmSynthetic
public suspend fun <R : Any> MiyousheVillaApi<R>.requestData(
    client: HttpClient,
    token: MiyousheVillaApiToken,
    decoder: Json = MiyousheVilla.DefaultJson
): R {
    val apiResult = requestResult(client, token, decoder)

    // check if success and return
    return apiResult.dataIfSuccess
}
