import { computed, ref } from 'vue'
import { userService, type UserRecord, type UserRole, type UserStatus } from '../services/user.service'

export function useAdminUsers() {
  const users = ref<UserRecord[]>([])
  const loading = ref(false)
  const error = ref<string | null>(null)

  const buyerUsers = computed(() => users.value.filter((u) => u.role === 'Buyer'))

  async function fetchUsers() {
    loading.value = true
    error.value = null
    try {
      users.value = await userService.getUsers()
    } catch (err: any) {
      error.value = err?.message || 'Failed to load users'
    } finally {
      loading.value = false
    }
  }

  async function approveKyc(id: number) {
    await userService.approveKyc(id)
    const target = users.value.find((u) => u.id === id)
    if (target) target.status = 'Active'
  }

  async function rejectKyc(id: number, reason: string, note?: string) {
    await userService.rejectKyc(id, reason, note)
    const target = users.value.find((u) => u.id === id)
    if (target) target.status = 'Pending'
  }

  async function updateStatus(id: number, status: UserStatus, reason?: string, note?: string) {
    await userService.updateUserStatus(id, status, reason, note)
    const target = users.value.find((u) => u.id === id)
    if (target) target.status = status
  }

  async function updateRole(id: number, role: UserRole) {
    await userService.updateUserRole(id, role)
    const target = users.value.find((u) => u.id === id)
    if (target) target.role = role
  }

  return {
    users,
    buyerUsers,
    loading,
    error,
    fetchUsers,
    approveKyc,
    rejectKyc,
    updateStatus,
    updateRole,
  }
}
