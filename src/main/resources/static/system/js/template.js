;(function(RCS){
    var getTemplates = function(callback){
        var list = {
            button: 'button.html',
            chat: 'chat.html',
            closebefore: 'closebefore.html',
            conversation: 'conversation.html',
            endconversation: 'endconversation.html',
            evaluate: 'evaluate.html',
            imageView: 'imageView.html',
            leaveword: 'leaveword.html',
            main: 'main.html',
            imMain: 'imMain.html',
            message: 'message.html',
            imMessage: 'imMessage.html',
            messageTemplate: 'messageTemplate.html',
            imMessageTemplate: 'imMessageTemplate.html',
            userInfo: 'userInfo.html',
        };
        var templates = {};
        for (var key in list) {
            var url = list[key];
            var html = RCS.templateCache[url];
            if (html) {
                templates[key] = html;
            } else {
                var xhr = new XMLHttpRequest();
                xhr.open('get', url, false);
                xhr.onreadystatechange = function(){
                    if (xhr.readyState == 4 && xhr.status == 200) {
                        templates[key] = xhr.responseText;
                    }
                }
                xhr.send(null);
            }

        }
        return templates;
    }
    RCS.getTemplates = getTemplates;
})(RCS);