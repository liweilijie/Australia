import 'ant-design-vue/dist/reset.css';

<template>
  <div class="about">
    <h1>This is an about page</h1>
    <a-button type="primary" shape="round" :size="size">
      <template #icon>
        <DownloadOutlined />
      </template>
      Download
    </a-button>
  </div>
</template>
