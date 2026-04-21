import { apiRequest } from '../utils/api'

export type UserStatus = 'Active' | 'Pending' | 'Suspended' | 'Banned'
export type UserRole = 'Buyer' | 'Farmer' | 'Admin'

export type UserRecord = {
  id: number
  name: string
  email: string
  role: UserRole
  province?: string
  trustScore?: number
  status: UserStatus
  orders?: number
  rating?: number
  disputes?: number
  joinedAt?: string
  submittedKycAt?: string
  docsComplete?: boolean
  docScore?: number
  docChecklist?: Array<{ label: string; ok: boolean }>
  daysSince?: number
  churnRisk?: 'High' | 'Medium' | 'Low'
}

type UsersResponse = UserRecord[] | { data?: UserRecord[] }

const FALLBACK_USERS: UserRecord[] = [
  {
    id: 1001,
    name: 'Sample Buyer',
    email: 'buyer@example.com',
    role: 'Buyer',
    province: 'Phnom Penh',
    trustScore: 72,
    status: 'Pending',
    orders: 3,
    rating: 4.2,
    disputes: 0,
    joinedAt: 'Apr 01',
    submittedKycAt: 'Apr 01',
    docsComplete: true,
    docScore: 100,
    docChecklist: [
      { label: 'National ID', ok: true },
      { label: 'Selfie photo', ok: true },
      { label: 'Land certificate', ok: false },
      { label: 'Farm photo', ok: false },
    ],
  },
]

function normalizeUsers(payload: UsersResponse): UserRecord[] {
  if (Array.isArray(payload)) return payload
  if (Array.isArray(payload?.data)) return payload.data
  return []
}

export const userService = {
  async getUsers(): Promise<UserRecord[]> {
    try {
      const result = await apiRequest<UsersResponse>('/users')
      const users = normalizeUsers(result)
      return users.length ? users : FALLBACK_USERS
    } catch {
      return FALLBACK_USERS
    }
  },

  async updateUserStatus(id: number, status: UserStatus, reason?: string, note?: string) {
    try {
      return await apiRequest(`/users/${id}`, {
        method: 'PATCH',
        body: { status, reason, note },
      })
    } catch {
      return { id, status, reason, note, offline: true }
    }
  },

  async updateUserRole(id: number, role: UserRole) {
    try {
      return await apiRequest(`/users/${id}`, {
        method: 'PATCH',
        body: { role },
      })
    } catch {
      return { id, role, offline: true }
    }
  },

  async approveKyc(id: number) {
    try {
      return await apiRequest(`/users/${id}/kyc/approve`, { method: 'POST' })
    } catch {
      return { id, approved: true, offline: true }
    }
  },

  async rejectKyc(id: number, reason: string, note?: string) {
    try {
      return await apiRequest(`/users/${id}/kyc/reject`, {
        method: 'POST',
        body: { reason, note },
      })
    } catch {
      return { id, rejected: true, reason, note, offline: true }
    }
  },
}
