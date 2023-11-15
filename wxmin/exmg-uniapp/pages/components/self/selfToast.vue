<template>
  <div v-if="visible" class="toast-overlay" :style="{ zIndex }">
    <div class="toast-container" :style="{ color: textColor }">
      <div v-if="isLoading" class="toast-loading"></div>
      <div class="toast-message">{{ message }}</div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      visible: false,
      message: '',
      isLoading: false,
      overlay: false,
      forbidClick: false,
      duration: 2000,
      zIndex: 1000,
      overlayColor: 'rgba(0, 0, 0, 0.5)',
      textColor: '#ffffff',
      timer: null
    };
  },
  methods: {
    toast(props) {
      this.visible = true;
      this.message = props.message;
      this.overlay = props.overlay || false;
      this.forbidClick = props.forbidClick || false;
      this.duration = props.duration || 2000;
      this.clearTimer();
      this.timer = setTimeout(() => {
        this.clear();
      }, this.duration);
      return this;
    },
    loading(props) {
      this.visible = true;
      this.message = props.message;
      this.isLoading = true;
      this.overlay = props.overlay || false;
      this.forbidClick = props.forbidClick || false;
      this.clearTimer();
      return this;
    },
    clear() {
      this.visible = false;
      this.message = '';
      this.isLoading = false;
      this.overlay = false;
      this.forbidClick = false;
      this.duration = 2000;
      clearTimeout(this.timer);
    },
    clearTimer() {
      clearTimeout(this.timer);
    }
  }
};
</script>

<style scoped>
.toast-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.toast-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 12px;
  border-radius: 4px;
  background-color: rgba(0, 0, 0, 0.8);
}

.toast-loading {
  width: 24px;
  height: 24px;
  margin-bottom: 8px;
  border: 4px solid rgba(255, 255, 255, 0.7);
  border-left-color: #f5f5f5;
  border-radius: 50%;
  animation: spin 1s linear infinite;
}

.toast-message {
  font-size: 14px;
  line-height: 1.5;
}

@keyframes spin {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}
</style>