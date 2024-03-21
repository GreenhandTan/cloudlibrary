(function(window) {
	/**
	 * 分页函数
	 * @date   2015-12-22
	 * @param  {[type]}   cur   当前页
	 * @param  {[type]}   total 总页数
	 * @param  {[type]}   len   显示多少页数
	 * @return {[type]}         [description]
	 */
	/*var pagination = {
		initPage: function(cur, total, len) {

		}
	}*/
    var pagination = function(options) {
        cur = parseInt(options.cur||1, 10);
        total = parseInt(options.total||0, 10);
        len = parseInt(options.len||0, 10);
        tar = document.getElementById(options.targetId);
        if(!tar || !total) {
            return '';
        }
        // 总数1页
        // cur 当前页，total总页数， len显示多少
        // 第一页，   1、总共不超过len条（下一页），2、超过5条（下一页  右more）
        // 中间       上一页/下一页         上一页/下一页  左右more
        // 最后一页   1、总数不超过5条（上一页），2、超过5条（上一页，左more）
        var html = '<ul class="pages"><li class="page">';
        if (total === 1) {
            html += '<a href="javascript:void(0);" class="page-index">' + 1 + '</a>'
        }
        if (cur !== 1 && total !== 1) {
            // 上一页
            html += '<a href="javascript:void(0);" class="prev page-index" data-index="' + (cur - 1) + '">&lt;</a>'
        }
        if (total > len && cur > Math.ceil(len / 2) && total !== 1) {
            // 左more
            html += '<span class="more">...</span>';
        }
        if (total !== 1) {
            // len 5 total 10  23456  10 6  4-6+1  5678  4-6 5   5-7 5678  7-7  45678910     4 2  53   len-cur+j
            // curr > Math.floor(len/2)    5   var a = Math.floor(len/2);  cur-a cur-a+1 cur-a+2
            // cur > 14    12 13 14 15 16
            var _l = len > total ? total : len;
            if (len < total && cur > Math.floor(len / 2) && (cur <= total - Math.floor(len / 2))) {
                for (var j = 1; j <= len; j++) {
                    if (cur === (cur - Math.ceil(len / 2) + j)) {
                        html += '<a href="javascript:void(0);" class="page-index cur" data-index="' + (cur - Math.ceil(len / 2) + j) + '">' + (cur - Math.ceil(len / 2) + j) + '</a>';
                        continue;
                    }
                    html += '<a href="javascript:void(0);" class="page-index" data-index="' + (cur - Math.ceil(len / 2) + j) + '">' + (cur - Math.ceil(len / 2) + j) + '</a>';
                }
            } else if (cur < len) {
                for (var i = 1; i <= _l; i++) {
                    if (cur === i) {
                        html += '<a href="javascript:void(0);" class="cur page-index" data-index="' + i + '">' + i + '</a>'
                        continue;
                    }
                    html += '<a href="javascript:void(0);" class="page-index" data-index="' + i + '">' + i + '</a>'
                }
            } else {
                for (var i = len - 1; i >= 0; i--) {
                    if (cur === (total - i)) {
                        html += '<a href="javascript:void(0);" class="cur page-index" data-index="' + (total - i) + '">' + (total - i) + '</a>'
                        continue;
                    }
                    html += '<a href="javascript:void(0);" class="page-index" data-index="' + (total - i) + '">' + (total - i) + '</a>'
                }
            }
        }

        if (total > len && cur < total - Math.floor(len / 2) && cur !== total && total !== 1) {
            // 右more
            html += '<span class="more">...</span>';
        }

        if (cur !== total && total !== 1) {
            // 下一页
            html += '<a href="javascript:void(0);" class="page-index next" data-index="' + (cur + 1) + '">&gt;</a>';
        }
        html += '<span class="page-total">共<span class="number">' + total + '</span>页</span>\
          <span class="page-go">到第<input class="w35 go" id="yeshu" type="text" value="">页</span>\
          <input type="button" class="gray-btn" id="go-search" value="确定">';
        tar.innerHTML = html;
        if(options.callback) {
            // 执行回调
            options.callback(total);
        }
    }

    // cmd || node.js
    if ( typeof module === "object" && module && typeof module.exports === "object" ) {
        module.exports = pagination;
    } else {
        window.pagination = pagination;
        // amd
        if ( typeof define === "function" && define.amd ) {
            define( "pagination", [], function () { return pagination; } );
        }
    }
})(window)
