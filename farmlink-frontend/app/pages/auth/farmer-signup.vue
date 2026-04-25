<template>
  <div class="min-h-screen bg-[#f5f6f3] px-4 py-10 flex justify-center">
    <div class="w-full max-w-6xl grid md:grid-cols-2 gap-10">
      <div class="bg-white rounded-3xl shadow-md overflow-hidden">
        <img
          src="https://images.unsplash.com/photo-1500382017468-9049fed747ef"
          class="h-[420px] w-full object-cover"
        >

        <div class="p-6">
          <h2 class="text-xl font-semibold text-slate-800">
            Precision Ecology for the modern farmer
          </h2>

          <p class="text-sm text-slate-500 mt-2">
            Join the most advanced digital network connecting sustainable harvests.
          </p>

          <div class="flex gap-4 mt-4">
            <div class="bg-slate-100 px-4 py-3 rounded-xl text-sm">
              <strong>4.2k</strong><br>Active Farms
            </div>
            <div class="bg-slate-100 px-4 py-3 rounded-xl text-sm">
              <strong>128TB</strong><br>Harvest Data
            </div>
          </div>
        </div>
      </div>

      <div>
        <span class="text-xs text-emerald-700 font-semibold">PARTNER ONBOARDING</span>
        <h1 class="text-3xl font-semibold mt-2 text-slate-900">
          Create Farmer Account
        </h1>
        <p class="text-sm text-slate-500 mb-6">
          Digitize your operations and access professional-grade tools.
        </p>

        <div class="grid grid-cols-2 gap-4 mb-6">
          <input v-model="form.name" placeholder="Full Name" class="input">
          <input v-model="form.phone" placeholder="Phone Number" class="input">
        </div>

        <div class="card">
          <h3 class="section-title">Verification Hub</h3>

          <div class="grid grid-cols-2 gap-4 mt-4">
            <div class="upload-box">ID Photo</div>
            <div class="upload-box">Farm Deed</div>
          </div>
        </div>

        <div class="card">
          <h3 class="section-title">Farm Logistics</h3>
          <input v-model="form.address" placeholder="Farm Address" class="input mt-3">
        </div>

        <div class="card flex items-center justify-between">
          <div>
            <p class="font-medium text-sm">Upload Aerial or Field Photo</p>
            <p class="text-xs text-slate-500">Recommended for verification</p>
          </div>
          <button class="btn-secondary" type="button">Select</button>
        </div>

        <div class="card">
          <h3 class="section-title">Product Catalog</h3>

          <div class="flex flex-wrap gap-2 mt-3">
            <span v-for="tag in tags" :key="tag" class="tag">{{ tag }}</span>
          </div>

          <div class="flex gap-2 mt-3">
            <input v-model="newTag" placeholder="Add product" class="input flex-1">
            <button @click="addTag" class="btn-secondary" type="button">Add</button>
          </div>
        </div>

        <label class="flex gap-2 text-sm mt-4">
          <input type="checkbox" v-model="form.agree">
          I confirm the accuracy of submitted data
        </label>

        <p v-if="errorMessage" class="text-red-600 text-sm mt-2">
          {{ errorMessage }}
        </p>

        <button
          @click="onSubmit"
          class="w-full bg-green-800 text-white py-3 rounded-xl mt-4 hover:bg-green-900"
        >
          Initialize Partner Account
        </button>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { reactive, ref } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();

const form = reactive({
  name: '',
  phone: '',
  address: '',
  agree: false,
});

const newTag = ref('');
const tags = ref(['Rice', 'Poultry']);
const errorMessage = ref('');

const addTag = () => {
  const value = newTag.value.trim();
  if (!value) {
    return;
  }

  if (!tags.value.includes(value)) {
    tags.value.push(value);
  }
  newTag.value = '';
};

const onSubmit = async () => {
  errorMessage.value = '';

  if (!form.agree) {
    errorMessage.value = 'Please confirm your data.';
    return;
  }

  console.log({ ...form, tags: tags.value });
  await router.push('/auth/signin?role=farmer');
};
</script>

<style scoped>
.input {
  @apply w-full px-3 py-2 rounded-xl border border-slate-300 text-sm outline-none focus:ring-2 focus:ring-emerald-500;
}

.card {
  @apply bg-white p-4 rounded-2xl shadow-sm mt-4;
}

.section-title {
  @apply text-sm font-semibold text-slate-700;
}

.upload-box {
  @apply border-2 border-dashed border-slate-300 rounded-xl h-24 flex items-center justify-center text-sm text-slate-400;
}

.btn-secondary {
  @apply bg-slate-200 px-4 py-2 rounded-xl text-sm hover:bg-slate-300;
}

.tag {
  @apply bg-emerald-100 text-emerald-700 px-3 py-1 rounded-full text-xs;
}
</style>