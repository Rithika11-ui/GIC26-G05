<template>
  <CartPage />
  <Shipping />
  <payment />
  <Sucess />
  <PaymentErrorPage />
  <QRPaymentPage />\
  <Appearance />
  <PurchaseHistory />
  <Index />
</template>
<script setup lang="ts">
import Appearance from './pages/admin/settings/appearance.vue';
import CartPage from './pages/user/cart.vue'
import PaymentErrorPage from './pages/user/checkout/PaymentErrorPage.vue';
import QRPaymentPage from './pages/user/checkout/QRPaymentPage.vue';
import Shipping from './pages/user/checkout/Shipping.vue';
import Sucess from './pages/user/checkout/Sucess.vue';
import payment from './pages/user/checkout/payment.vue';
import PurchaseHistory from './pages/user/settings/PurchaseHistory.vue';
</script>