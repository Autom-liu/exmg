<template>
  <div class="self-progress">
    <div class="progress-bar">
      <div class="progress" :style="{ width: percentage + '%', backgroundColor: color }"></div>
	  <div class="slider" :style="{ left: (percentage-5) + '%', backgroundColor: color }">{{ pivotText }}</div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    percentage: {
      type: Number,
      required: true,
      validator: (value) => value >= 0 && value <= 100,
    },
    pivotText: String,
    color: String,
  },
  computed: {
    colorClass() {
      return this.color ? `color-${this.color}` : '';
    },
  },
};
</script>

<style scoped>
.self-progress {
  width: 100%;
  background-color: lightgray;
  border-radius: 5px;
  position: relative;
}

.progress-bar {
  height: 10px;
  position: relative;
}

.progress {
  height: 100%;
  transition: width 0.5s;
}

.slider {
  position: absolute;
  top: -3px;
  font-size: 10px;
  width: 30px;
  height: 15px;
  text-align: center;
  line-height: 15px;
  color: #fff;
  border-radius: 10px;
  display: inline-block;
}

.color-red {
  background-color: red;
}

.color-green {
  background-color: green;
}

/* Add more color classes as needed */
</style>
