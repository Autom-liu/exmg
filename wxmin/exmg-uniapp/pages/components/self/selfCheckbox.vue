<template>
  <view class="van-checkbox" @click="toggleCheckbox">
    <view class="checkbox-box" :class="{ 'checked': value }">
      <uni-icons v-if="value" type="checkmarkempty" :color="checkedColor" size="20"></uni-icons>
	  <uni-icons v-else type="smallcircle-filled" size="32"></uni-icons>
    </view>
    <span class="checkbox-label">
      <slot></slot>
    </span>
  </view>
</template>

<script>
export default {
  name: 'selfCheckbox',
  props: {
    value: Boolean, // Indicates whether the checkbox is checked or not
    checkedColor: String, // Color when the checkbox is checked
  },
  methods: {
    toggleCheckbox() {
      this.$emit('change', !this.value); // Emit change event with inverted value
    },
  },
};
</script>

<style scoped>
.van-checkbox {
  display: flex;
  align-items: center;
  margin: 10px 0;
}

.checkbox-box {
  width: 20px; /* You can adjust the width as needed */
  height: 20px;
  border: 1px solid #000;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
}

.checkbox-label {
  margin-left: 10px; /* Adjust margin as needed */
  flex-grow: 1;
  cursor: pointer;
}
</style>
