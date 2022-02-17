window.watsonAssistantChatOptions = {
    integrationID: "2bbfa4a2-89bd-44ee-8859-8d7987b300bd", // The ID of this integration.
    region: "us-south", // The region your integration is hosted in.
    serviceInstanceID: "bde79a3e-84bc-4833-9e04-edb89edce817", // The ID of your service instance.
    onLoad: function(instance) { instance.render(); }
  };
setTimeout(function(){
  const t=document.createElement('script');
  t.src="https://web-chat.global.assistant.watson.appdomain.cloud/loadWatsonAssistantChat.js";
  document.head.appendChild(t);
});