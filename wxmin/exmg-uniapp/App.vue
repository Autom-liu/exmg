<script>
	export default {
		onLaunch: function() {
			console.warn('当前组件仅支持 uni_modules 目录结构 ，请升级 HBuilderX 到 3.1.0 版本以上！')
			console.log('App Launch')
		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		}
	}
</script>

<style lang="scss">
	/*每个页面公共css */
	@import '@/uni_modules/uni-scss/index.scss';
	/* #ifndef APP-NVUE */
	@import '@/static/customicons.css';
	/* #endif */
	// 设置整个项目的背景色
	page {
		background-color: #f5f5f5;
	}
	
	.container {
	  height: 100%;
	  display: flex;
	  flex-direction: column;
	  align-items: center;
	  justify-content: space-between;
	  padding: 200rpx 0;
	  box-sizing: border-box;
	}
	
	.uni-header-image {
		width: 100%;
		height: 140px;
	}
	
	.uni-icon {
	    font-family: uniicons;
	    font-weight: normal;
	}
	
	.uni-container {
	    padding: 15px;
	    background-color: #f8f8f8;
	}
	
	.uni-panel {
	    margin-bottom: 12px;
	}
	
	.uni-panel-h {
		display: flex;
	    background-color: #ffffff;
	    flex-direction: row;
		justify-content: space-between;
	    padding: 12px;
		cursor: pointer;
	}
	
	.uni-panel-h-on {
	    background-color: #f0f0f0;
	}
	
	.uni-panel-text {
	    flex: 1;
	    color: #000000;
	    font-size: 14px;
	    font-weight: normal;
	}
	
	.uni-panel-icon {
	    margin-left: 15px;
	    color: #999999;
	    font-size: 14px;
	    font-weight: normal;
	    transform: rotate(0deg);
	    transition-duration: 0s;
	    transition-property: transform;
	}
	
	.uni-panel-icon-on {
	    transform: rotate(180deg);
	}
	
	.uni-navigate-item {
		/* #ifdef H5 */
		display: flex;
		/* #endif */
	    flex-direction: row;
	    align-items: center;
	    background-color: #FFFFFF;
	    border-top-style: solid;
	    border-top-color: #f0f0f0;
	    border-top-width: 1px;
	    padding: 12px;
		/* #ifdef H5 */
		cursor: pointer;
		/* #endif */
	}
	
	.uni-navigate-item:active {
	    background-color: #f8f8f8;
	}
	
	.uni-navigate-text {
	    flex: 1;
	    color: #000000;
	    font-size: 14px;
	    font-weight: normal;
	}
	
	.uni-navigate-icon {
	    margin-left: 15px;
	    color: #999999;
	    font-size: 14px;
	    font-weight: normal;
	}
	
	/* this rule will be remove */
	* {
	  transition: width 2s;
	  -moz-transition: width 2s;
	  -webkit-transition: width 2s;
	  -o-transition: width 2s;
	}
</style>
