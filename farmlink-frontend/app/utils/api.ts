export type ApiRequestOptions = {
  method?: 'GET' | 'POST' | 'PUT' | 'PATCH' | 'DELETE'
  body?: unknown
  token?: string
  headers?: Record<string, string>
}

const DEFAULT_API_BASE = 'http://localhost:3000'

function getBaseUrl() {
  return (import.meta as any)?.env?.VITE_API_BASE_URL || DEFAULT_API_BASE
}

export async function apiRequest<T>(path: string, options: ApiRequestOptions = {}): Promise<T> {
  const { method = 'GET', body, token, headers = {} } = options

  const requestHeaders: Record<string, string> = {
    'Content-Type': 'application/json',
    ...headers,
  }

  if (token) {
    requestHeaders.Authorization = `Bearer ${token}`
  }

  const response = await fetch(`${getBaseUrl()}${path}`, {
    method,
    headers: requestHeaders,
    body: body != null ? JSON.stringify(body) : undefined,
  })

  if (!response.ok) {
    let message = `Request failed (${response.status})`
    try {
      const errorBody = await response.json()
      message = errorBody?.message || message
    } catch {}
    throw new Error(message)
  }

  if (response.status === 204) {
    return undefined as T
  }

  return (await response.json()) as T
}
