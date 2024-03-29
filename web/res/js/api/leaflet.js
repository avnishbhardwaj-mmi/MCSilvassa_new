!function(d, g, c) {
    var j = d.M, h = {};
    h.version = "0.0.1", "object" == typeof module && "object" == typeof module.exports ? module.exports = h : "function" == typeof define && define.amd && define(h), h.noConflict = function() {
        return d.M = j, this
    }, d.M = h, d.L = h, h.Util = {extend: function(k) {
            var m, a, q, p, l = Array.prototype.slice.call(arguments, 1);
            for (a = 0, q = l.length; q > a; a++) {
                p = l[a] || {};
                for (m in p) {
                    p.hasOwnProperty(m) && (k[m] = p[m])
                }
            }
            return k
        }, bind: function(k, l) {
            var a = arguments.length > 2 ? Array.prototype.slice.call(arguments, 2) : null;
            return function() {
                return k.apply(l, a || arguments)
            }
        }, stamp: function() {
            var a = 0, i = "_leaflet_id";
            return function(e) {
                return e[i] = e[i] || ++a, e[i]
            }
        }(), invokeEach: function(k, l, a) {
            var p, m;
            if ("object" == typeof k) {
                m = Array.prototype.slice.call(arguments, 3);
                for (p in k) {
                    l.apply(a, [p, k[p]].concat(m))
                }
                return !0
            }
            return !1
        }, limitExecByInterval: function(k, m, a) {
            var q, p;
            return function l() {
                var e = arguments;
                return q ? void (p = !0) : (q = !0, setTimeout(function() {
                    q = !1, p && (l.apply(a, e), p = !1)
                }, m), void k.apply(a, e))
            }
        }, falseFn: function() {
            return !1
        }, formatNum: function(k, l) {
            var a = Math.pow(10, l || 5);
            return Math.round(k * a) / a
        }, trim: function(a) {
            return a.trim ? a.trim() : a.replace(/^\s+|\s+$/g, "")
        }, splitWords: function(a) {
            return h.Util.trim(a).split(/\s+/)
        }, setOptions: function(a, i) {
            return a.options = h.extend({}, a.options, i), a.options
        }, getParamString: function(l, p, k) {
            var r = [];
            for (var q in l) {
                r.push(encodeURIComponent(k ? q.toUpperCase() : q) + "=" + encodeURIComponent(l[q]))
            }
            var a = (p && -1 !== p.indexOf("?") ? "&" : "?") + r.join("&");
            return a
        }, template: function(a, i) {
            return a.replace(/\{ *([\w_]+) *\}/g, function(e, l) {
                var k = i[l];
                if (k === c) {
                    throw new Error("No value provided for variable " + e)
                }
                return"function" == typeof k && (k = k(i)), k
            })
        }, isArray: Array.isArray || function(a) {
            return"[object Array]" === Object.prototype.toString.call(a)
        }, emptyImageUrl: "data:image/gif;base64,R0lGODlhAQABAAD/ACwAAAAAAQABAAACADs="}, function() {
        function o(q) {
            var a, s, r = ["webkit", "moz", "o", "ms"];
            for (a = 0; a < r.length && !s; a++) {
                s = d[r[a] + q]
            }
            return s
        }
        function l(n) {
            var a = +new Date, q = Math.max(0, 16 - (a - p));
            return p = a + q, d.setTimeout(n, q)
        }
        var p = 0, m = d.requestAnimationFrame || o("RequestAnimationFrame") || l, k = d.cancelAnimationFrame || o("CancelAnimationFrame") || o("CancelRequestAnimationFrame") || function(a) {
            d.clearTimeout(a)
        };
        h.Util.requestAnimFrame = function(s, t, i, q) {
            return s = h.bind(s, t), i && m === l ? void s() : m.call(d, s, q)
        }, h.Util.cancelAnimFrame = function(a) {
            a && k.call(d, a)
        }
    }(), h.extend = h.Util.extend, h.bind = h.Util.bind, h.stamp = h.Util.stamp, h.setOptions = h.Util.setOptions, h.Class = function() {
    }, h.Class.extend = function(m) {
        var p = function() {
            this.initialize && this.initialize.apply(this, arguments), this._initHooks && this.callInitHooks()
        }, l = function() {
        };
        l.prototype = this.prototype;
        var q = new l;
        q.constructor = p, p.prototype = q;
        for (var o in this) {
            this.hasOwnProperty(o) && "prototype" !== o && (p[o] = this[o])
        }
        m.statics && (h.extend(p, m.statics), delete m.statics), m.includes && (h.Util.extend.apply(null, [q].concat(m.includes)), delete m.includes), m.options && q.options && (m.options = h.extend({}, q.options, m.options)), h.extend(q, m), q._initHooks = [];
        var k = this;
        return p.__super__ = k.prototype, q.callInitHooks = function() {
            if (!this._initHooksCalled) {
                k.prototype.callInitHooks && k.prototype.callInitHooks.call(this), this._initHooksCalled = !0;
                for (var a = 0, i = q._initHooks.length; i > a; a++) {
                    q._initHooks[a].call(this)
                }
            }
        }, p
    }, h.Class.include = function(a) {
        h.extend(this.prototype, a)
    }, h.Class.mergeOptions = function(a) {
        h.extend(this.prototype.options, a)
    }, h.Class.addInitHook = function(k) {
        var l = Array.prototype.slice.call(arguments, 1), a = "function" == typeof k ? k : function() {
            this[k].apply(this, l)
        };
        this.prototype._initHooks = this.prototype._initHooks || [], this.prototype._initHooks.push(a)
    };
    var f = "_leaflet_events";
    h.Mixin = {}, h.Mixin.Events = {addEventListener: function(B, w, s) {
            if (h.Util.invokeEach(B, this.addEventListener, this, w, s)) {
                return this
            }
            var o, z, k, v, q, A, y, x = this[f] = this[f] || {}, m = s && s !== this && h.stamp(s);
            for (B = h.Util.splitWords(B), o = 0, z = B.length; z > o; o++) {
                k = {action: w, context: s || this}, v = B[o], m ? (q = v + "_idx", A = q + "_len", y = x[q] = x[q] || {}, y[m] || (y[m] = [], x[A] = (x[A] || 0) + 1), y[m].push(k)) : (x[v] = x[v] || [], x[v].push(k))
            }
            return this
        }, hasEventListeners: function(a) {
            var i = this[f];
            return !!i && (a in i && i[a].length > 0 || a + "_idx" in i && i[a + "_idx_len"] > 0)
        }, removeEventListener: function(E, y, w) {
            if (!this[f]) {
                return this
            }
            if (!E) {
                return this.clearAllEventListeners()
            }
            if (h.Util.invokeEach(E, this.removeEventListener, this, y, w)) {
                return this
            }
            var q, B, k, x, v, D, A, z, o, C = this[f], s = w && w !== this && h.stamp(w);
            for (E = h.Util.splitWords(E), q = 0, B = E.length; B > q; q++) {
                if (k = E[q], D = k + "_idx", A = D + "_len", z = C[D], y) {
                    if (x = s && z ? z[s] : C[k]) {
                        for (v = x.length - 1; v >= 0; v--) {
                            x[v].action !== y || w && x[v].context !== w || (o = x.splice(v, 1), o[0].action = h.Util.falseFn)
                        }
                        w && z && 0 === x.length && (delete z[s], C[A]--)
                    }
                } else {
                    delete C[k], delete C[D], delete C[A]
                }
            }
            return this
        }, clearAllEventListeners: function() {
            return delete this[f], this
        }, fireEvent: function(x, s) {
            if (!this.hasEventListeners(x)) {
                return this
            }
            var p, m, v, k, q, o = h.Util.extend({}, s, {type: x, target: this}), w = this[f];
            if (w[x]) {
                for (p = w[x].slice(), m = 0, v = p.length; v > m; m++) {
                    p[m].action.call(p[m].context, o)
                }
            }
            k = w[x + "_idx"];
            for (q in k) {
                if (p = k[q].slice()) {
                    for (m = 0, v = p.length; v > m; m++) {
                        p[m].action.call(p[m].context, o)
                    }
                }
            }
            return this
        }, addOneTimeEventListener: function(k, l, a) {
            if (h.Util.invokeEach(k, this.addOneTimeEventListener, this, l, a)) {
                return this
            }
            var m = h.bind(function() {
                this.removeEventListener(k, l, a).removeEventListener(k, m, a)
            }, this);
            return this.addEventListener(k, l, a).addEventListener(k, m, a)
        }}, h.Mixin.Events.on = h.Mixin.Events.addEventListener, h.Mixin.Events.off = h.Mixin.Events.removeEventListener, h.Mixin.Events.once = h.Mixin.Events.addOneTimeEventListener, h.Mixin.Events.fire = h.Mixin.Events.fireEvent, function() {
        var D = "ActiveXObject" in d, A = D && !g.addEventListener, M = navigator.userAgent.toLowerCase(), B = -1 !== M.indexOf("webkit"), G = -1 !== M.indexOf("chrome"), F = -1 !== M.indexOf("phantom"), z = -1 !== M.indexOf("android"), K = -1 !== M.search("android [23]"), J = -1 !== M.indexOf("gecko"), C = typeof orientation != c + "", N = d.navigator && d.navigator.msPointerEnabled && d.navigator.msMaxTouchPoints && !d.PointerEvent, E = d.PointerEvent && d.navigator.pointerEnabled && d.navigator.maxTouchPoints || N, I = "devicePixelRatio" in d && d.devicePixelRatio > 1 || "matchMedia" in d && d.matchMedia("(min-resolution:144dpi)") && d.matchMedia("(min-resolution:144dpi)").matches, H = g.documentElement, t = D && "transition" in H.style, k = "WebKitCSSMatrix" in d && "m11" in new d.WebKitCSSMatrix && !K, e = "MozPerspective" in H.style, i = "OTransition" in H.style, o = !d.L_DISABLE_3D && (t || k || e || i) && !F, q = !d.L_NO_TOUCH && !F && function() {
            var l = "ontouchstart";
            if (E || l in H) {
                return !0
            }
            var a = g.createElement("div"), m = !1;
            return a.setAttribute ? (a.setAttribute(l, "return;"), "function" == typeof a[l] && (m = !0), a.removeAttribute(l), a = null, m) : !1
        }();
        h.Browser = {ie: D, ielt9: A, webkit: B, gecko: J && !B && !d.opera && !D, android: z, android23: K, chrome: G, ie3d: t, webkit3d: k, gecko3d: e, opera3d: i, any3d: o, mobile: C, mobileWebkit: C && B, mobileWebkit3d: C && k, mobileOpera: C && d.opera, touch: q, msPointer: N, pointer: E, retina: I}
    }(), h.Point = function(k, l, a) {
        this.x = a ? Math.round(k) : k, this.y = a ? Math.round(l) : l
    }, h.Point.prototype = {clone: function() {
            return new h.Point(this.x, this.y)
        }, add: function(a) {
            return this.clone()._add(h.point(a))
        }, _add: function(a) {
            return this.x += a.x, this.y += a.y, this
        }, subtract: function(a) {
            return this.clone()._subtract(h.point(a))
        }, _subtract: function(a) {
            return this.x -= a.x, this.y -= a.y, this
        }, divideBy: function(a) {
            return this.clone()._divideBy(a)
        }, _divideBy: function(a) {
            return this.x /= a, this.y /= a, this
        }, multiplyBy: function(a) {
            return this.clone()._multiplyBy(a)
        }, _multiplyBy: function(a) {
            return this.x *= a, this.y *= a, this
        }, round: function() {
            return this.clone()._round()
        }, _round: function() {
            return this.x = Math.round(this.x), this.y = Math.round(this.y), this
        }, floor: function() {
            return this.clone()._floor()
        }, _floor: function() {
            return this.x = Math.floor(this.x), this.y = Math.floor(this.y), this
        }, distanceTo: function(k) {
            k = h.point(k);
            var l = k.x - this.x, a = k.y - this.y;
            return Math.sqrt(l * l + a * a)
        }, equals: function(a) {
            return a = h.point(a), a.x === this.x && a.y === this.y
        }, contains: function(a) {
            return a = h.point(a), Math.abs(a.x) <= Math.abs(this.x) && Math.abs(a.y) <= Math.abs(this.y)
        }, toString: function() {
            return"Point(" + h.Util.formatNum(this.x) + ", " + h.Util.formatNum(this.y) + ")"
        }}, h.point = function(a, i, k) {
        return a instanceof h.Point ? a : h.Util.isArray(a) ? new h.Point(a[0], a[1]) : a === c || null === a ? a : new h.Point(a, i, k)
    }, h.Bounds = function(k, l) {
        if (k) {
            for (var a = l ? [k, l] : k, p = 0, m = a.length; m > p; p++) {
                this.extend(a[p])
            }
        }
    }, h.Bounds.prototype = {extend: function(a) {
            return a = h.point(a), this.min || this.max ? (this.min.x = Math.min(a.x, this.min.x), this.max.x = Math.max(a.x, this.max.x), this.min.y = Math.min(a.y, this.min.y), this.max.y = Math.max(a.y, this.max.y)) : (this.min = a.clone(), this.max = a.clone()), this
        }, getCenter: function(a) {
            return new h.Point((this.min.x + this.max.x) / 2, (this.min.y + this.max.y) / 2, a)
        }, getBottomLeft: function() {
            return new h.Point(this.min.x, this.max.y)
        }, getTopRight: function() {
            return new h.Point(this.max.x, this.min.y)
        }, getSize: function() {
            return this.max.subtract(this.min)
        }, contains: function(k) {
            var l, a;
            return k = "number" == typeof k[0] || k instanceof h.Point ? h.point(k) : h.bounds(k), k instanceof h.Bounds ? (l = k.min, a = k.max) : l = a = k, l.x >= this.min.x && a.x <= this.max.x && l.y >= this.min.y && a.y <= this.max.y
        }, intersects: function(m) {
            m = h.bounds(m);
            var q = this.min, l = this.max, u = m.min, o = m.max, k = o.x >= q.x && u.x <= l.x, p = o.y >= q.y && u.y <= l.y;
            return k && p
        }, isValid: function() {
            return !(!this.min || !this.max)
        }}, h.bounds = function(a, i) {
        return !a || a instanceof h.Bounds ? a : new h.Bounds(a, i)
    }, h.Transformation = function(k, l, a, m) {
        this._a = k, this._b = l, this._c = a, this._d = m
    }, h.Transformation.prototype = {transform: function(a, i) {
            return this._transform(a.clone(), i)
        }, _transform: function(a, i) {
            return i = i || 1, a.x = i * (this._a * a.x + this._b), a.y = i * (this._c * a.y + this._d), a
        }, untransform: function(a, i) {
            return i = i || 1, new h.Point((a.x / i - this._b) / this._a, (a.y / i - this._d) / this._c)
        }}, h.DomUtil = {get: function(a) {
            return"string" == typeof a ? g.getElementById(a) : a
        }, getStyle: function(e, a) {
            var l = e.style[a];
            if (!l && e.currentStyle && (l = e.currentStyle[a]), (!l || "auto" === l) && g.defaultView) {
                var k = g.defaultView.getComputedStyle(e, null);
                l = k ? k[a] : null
            }
            return"auto" === l ? null : l
        }, getViewportOffset: function(x) {
            var o, k = 0, y = 0, v = x, e = g.body, p = g.documentElement;
            do {
                if (k += v.offsetTop || 0, y += v.offsetLeft || 0, k += parseInt(h.DomUtil.getStyle(v, "borderTopWidth"), 10) || 0, y += parseInt(h.DomUtil.getStyle(v, "borderLeftWidth"), 10) || 0, o = h.DomUtil.getStyle(v, "position"), v.offsetParent === e && "absolute" === o) {
                    break
                }
                if ("fixed" === o) {
                    k += e.scrollTop || p.scrollTop || 0, y += e.scrollLeft || p.scrollLeft || 0;
                    break
                }
                if ("relative" === o && !v.offsetLeft) {
                    var m = h.DomUtil.getStyle(v, "width"), w = h.DomUtil.getStyle(v, "max-width"), q = v.getBoundingClientRect();
                    ("none" !== m || "none" !== w) && (y += q.left + v.clientLeft), k += q.top + (e.scrollTop || p.scrollTop || 0);
                    break
                }
                v = v.offsetParent
            } while (v);
            v = x;
            do {
                if (v === e) {
                    break
                }
                k -= v.scrollTop || 0, y -= v.scrollLeft || 0, v = v.parentNode
            } while (v);
            return new h.Point(y, k)
        }, documentIsLtr: function() {
            return h.DomUtil._docIsLtrCached || (h.DomUtil._docIsLtrCached = !0, h.DomUtil._docIsLtr = "ltr" === h.DomUtil.getStyle(g.body, "direction")), h.DomUtil._docIsLtr
        }, create: function(e, a, l) {
            var k = g.createElement(e);
            return k.className = a, l && l.appendChild(k), k
        }, hasClass: function(a, i) {
            if (a.classList !== c) {
                return a.classList.contains(i)
            }
            var k = h.DomUtil._getClass(a);
            return k.length > 0 && new RegExp("(^|\\s)" + i + "(\\s|$)").test(k)
        }, addClass: function(k, o) {
            if (k.classList !== c) {
                for (var p = h.Util.splitWords(o), l = 0, i = p.length; i > l; l++) {
                    k.classList.add(p[l])
                }
            } else {
                if (!h.DomUtil.hasClass(k, o)) {
                    var m = h.DomUtil._getClass(k);
                    h.DomUtil._setClass(k, (m ? m + " " : "") + o)
                }
            }
        }, removeClass: function(a, i) {
            a.classList !== c ? a.classList.remove(i) : h.DomUtil._setClass(a, h.Util.trim((" " + h.DomUtil._getClass(a) + " ").replace(" " + i + " ", " ")))
        }, _setClass: function(a, i) {
            a.className.baseVal === c ? a.className = i : a.className.baseVal = i
        }, _getClass: function(a) {
            return a.className.baseVal === c ? a.className : a.className.baseVal
        }, setOpacity: function(k, l) {
            if ("opacity" in k.style) {
                k.style.opacity = l
            } else {
                if ("filter" in k.style) {
                    var a = !1, p = "DXImageTransform.Microsoft.Alpha";
                    try {
                        a = k.filters.item(p)
                    } catch (m) {
                        if (1 === l) {
                            return
                        }
                    }
                    l = Math.round(100 * l), a ? (a.Enabled = 100 !== l, a.Opacity = l) : k.style.filter += " progid:" + p + "(opacity=" + l + ")"
                }
            }
        }, testProp: function(e) {
            for (var a = g.documentElement.style, k = 0; k < e.length; k++) {
                if (e[k] in a) {
                    return e[k]
                }
            }
            return !1
        }, getTranslateString: function(k) {
            var l = h.Browser.webkit3d, a = "translate" + (l ? "3d" : "") + "(", m = (l ? ",0" : "") + ")";
            return a + k.x + "px," + k.y + "px" + m
        }, getScaleString: function(k, l) {
            var a = h.DomUtil.getTranslateString(l.add(l.multiplyBy(-1 * k))), m = " scale(" + k + ") ";
            return a + m
        }, setPosition: function(k, l, a) {
            k._leaflet_pos = l, !a && h.Browser.any3d ? k.style[h.DomUtil.TRANSFORM] = h.DomUtil.getTranslateString(l) : (k.style.left = l.x + "px", k.style.top = l.y + "px")
        }, getPosition: function(a) {
            return a._leaflet_pos
        }}, h.DomUtil.TRANSFORM = h.DomUtil.testProp(["transform", "WebkitTransform", "OTransform", "MozTransform", "msTransform"]), h.DomUtil.TRANSITION = h.DomUtil.testProp(["webkitTransition", "transition", "OTransition", "MozTransition", "msTransition"]), h.DomUtil.TRANSITION_END = "webkitTransition" === h.DomUtil.TRANSITION || "OTransition" === h.DomUtil.TRANSITION ? h.DomUtil.TRANSITION + "End" : "transitionend", function() {
        if ("onselectstart" in g) {
            h.extend(h.DomUtil, {disableTextSelection: function() {
                    h.DomEvent.on(d, "selectstart", h.DomEvent.preventDefault)
                }, enableTextSelection: function() {
                    h.DomEvent.off(d, "selectstart", h.DomEvent.preventDefault)
                }})
        } else {
            var a = h.DomUtil.testProp(["userSelect", "WebkitUserSelect", "OUserSelect", "MozUserSelect", "msUserSelect"]);
            h.extend(h.DomUtil, {disableTextSelection: function() {
                    if (a) {
                        var e = g.documentElement.style;
                        this._userSelect = e[a], e[a] = "none"
                    }
                }, enableTextSelection: function() {
                    a && (g.documentElement.style[a] = this._userSelect, delete this._userSelect)
                }})
        }
        h.extend(h.DomUtil, {disableImageDrag: function() {
                h.DomEvent.on(d, "dragstart", h.DomEvent.preventDefault)
            }, enableImageDrag: function() {
                h.DomEvent.off(d, "dragstart", h.DomEvent.preventDefault)
            }})
    }(), h.LatLng = function(a, i, k) {
        if (a = parseFloat(a), i = parseFloat(i), isNaN(a) || isNaN(i)) {
            throw new Error("Invalid LatLng object: (" + a + ", " + i + ")")
        }
        this.lat = a, this.lng = i, k !== c && (this.alt = parseFloat(k))
    }, h.extend(h.LatLng, {DEG_TO_RAD: Math.PI / 180, RAD_TO_DEG: 180 / Math.PI, MAX_MARGIN: 1e-9}), h.LatLng.prototype = {equals: function(a) {
            if (!a) {
                return !1
            }
            a = h.latLng(a);
            var i = Math.max(Math.abs(this.lat - a.lat), Math.abs(this.lng - a.lng));
            return i <= h.LatLng.MAX_MARGIN
        }, toString: function(a) {
            return"LatLng(" + h.Util.formatNum(this.lat, a) + ", " + h.Util.formatNum(this.lng, a) + ")"
        }, distanceTo: function(y) {
            y = h.latLng(y);
            var v = 6378137, p = h.LatLng.DEG_TO_RAD, m = (y.lat - this.lat) * p, z = (y.lng - this.lng) * p, w = this.lat * p, k = y.lat * p, q = Math.sin(m / 2), o = Math.sin(z / 2), x = q * q + o * o * Math.cos(w) * Math.cos(k);
            return 2 * v * Math.atan2(Math.sqrt(x), Math.sqrt(1 - x))
        }, wrap: function(k, l) {
            var a = this.lng;
            return k = k || -180, l = l || 180, a = (a + l) % (l - k) + (k > a || a === l ? l : k), new h.LatLng(this.lat, a)
        }}, h.latLng = function(a, i) {
        return a instanceof h.LatLng ? a : h.Util.isArray(a) ? "number" == typeof a[0] || "string" == typeof a[0] ? new h.LatLng(a[0], a[1], a[2]) : null : a === c || null === a ? a : "object" == typeof a && "lat" in a ? new h.LatLng(a.lat, "lng" in a ? a.lng : a.lon) : i === c ? null : new h.LatLng(a, i)
    }, h.LatLngBounds = function(k, l) {
        if (k) {
            for (var a = l ? [k, l] : k, p = 0, m = a.length; m > p; p++) {
                this.extend(a[p])
            }
        }
    }, h.LatLngBounds.prototype = {extend: function(a) {
            if (!a) {
                return this
            }
            var i = h.latLng(a);
            return a = null !== i ? i : h.latLngBounds(a), a instanceof h.LatLng ? this._southWest || this._northEast ? (this._southWest.lat = Math.min(a.lat, this._southWest.lat), this._southWest.lng = Math.min(a.lng, this._southWest.lng), this._northEast.lat = Math.max(a.lat, this._northEast.lat), this._northEast.lng = Math.max(a.lng, this._northEast.lng)) : (this._southWest = new h.LatLng(a.lat, a.lng), this._northEast = new h.LatLng(a.lat, a.lng)) : a instanceof h.LatLngBounds && (this.extend(a._southWest), this.extend(a._northEast)), this
        }, pad: function(k) {
            var m = this._southWest, a = this._northEast, o = Math.abs(m.lat - a.lat) * k, l = Math.abs(m.lng - a.lng) * k;
            return new h.LatLngBounds(new h.LatLng(m.lat - o, m.lng - l), new h.LatLng(a.lat + o, a.lng + l))
        }, getCenter: function() {
            return new h.LatLng((this._southWest.lat + this._northEast.lat) / 2, (this._southWest.lng + this._northEast.lng) / 2)
        }, getSouthWest: function() {
            return this._southWest
        }, getNorthEast: function() {
            return this._northEast
        }, getNorthWest: function() {
            return new h.LatLng(this.getNorth(), this.getWest())
        }, getSouthEast: function() {
            return new h.LatLng(this.getSouth(), this.getEast())
        }, getWest: function() {
            return this._southWest.lng
        }, getSouth: function() {
            return this._southWest.lat
        }, getEast: function() {
            return this._northEast.lng
        }, getNorth: function() {
            return this._northEast.lat
        }, contains: function(k) {
            k = "number" == typeof k[0] || k instanceof h.LatLng ? h.latLng(k) : h.latLngBounds(k);
            var m, a, o = this._southWest, l = this._northEast;
            return k instanceof h.LatLngBounds ? (m = k.getSouthWest(), a = k.getNorthEast()) : m = a = k, m.lat >= o.lat && a.lat <= l.lat && m.lng >= o.lng && a.lng <= l.lng
        }, intersects: function(m) {
            m = h.latLngBounds(m);
            var q = this._southWest, l = this._northEast, u = m.getSouthWest(), o = m.getNorthEast(), k = o.lat >= q.lat && u.lat <= l.lat, p = o.lng >= q.lng && u.lng <= l.lng;
            return k && p
        }, toBBoxString: function() {
            return[this.getWest(), this.getSouth(), this.getEast(), this.getNorth()].join(",")
        }, equals: function(a) {
            return a ? (a = h.latLngBounds(a), this._southWest.equals(a.getSouthWest()) && this._northEast.equals(a.getNorthEast())) : !1
        }, isValid: function() {
            return !(!this._southWest || !this._northEast)
        }}, h.latLngBounds = function(a, i) {
        return !a || a instanceof h.LatLngBounds ? a : new h.LatLngBounds(a, i)
    }, h.Projection = {}, h.Projection.SphericalMercator = {MAX_LATITUDE: 85.0511287798, project: function(m) {
            var p = h.LatLng.DEG_TO_RAD, l = this.MAX_LATITUDE, q = Math.max(Math.min(l, m.lat), -l), o = m.lng * p, k = q * p;
            return k = Math.log(Math.tan(Math.PI / 4 + k / 2)), new h.Point(o, k)
        }, unproject: function(k) {
            var l = h.LatLng.RAD_TO_DEG, a = k.x * l, m = (2 * Math.atan(Math.exp(k.y)) - Math.PI / 2) * l;
            return new h.LatLng(m, a)
        }}, h.Projection.LonLat = {project: function(a) {
            return new h.Point(a.lng, a.lat)
        }, unproject: function(a) {
            return new h.LatLng(a.y, a.x)
        }}, h.CRS = {latLngToPoint: function(k, l) {
            var a = this.projection.project(k), m = this.scale(l);
            return this.transformation._transform(a, m)
        }, pointToLatLng: function(k, l) {
            var a = this.scale(l), m = this.transformation.untransform(k, a);
            return this.projection.unproject(m)
        }, project: function(a) {
            return this.projection.project(a)
        }, scale: function(a) {
            return 256 * Math.pow(2, a)
        }, getSize: function(a) {
            var i = this.scale(a);
            return h.point(i, i)
        }}, h.CRS.Simple = h.extend({}, h.CRS, {projection: h.Projection.LonLat, transformation: new h.Transformation(1, 0, -1, 0), scale: function(a) {
            return Math.pow(2, a)
        }}), h.CRS.EPSG3857 = h.extend({}, h.CRS, {code: "EPSG:3857", projection: h.Projection.SphericalMercator, transformation: new h.Transformation(0.5 / Math.PI, 0.5, -0.5 / Math.PI, 0.5), project: function(k) {
            var l = this.projection.project(k), a = 6378137;
            return l.multiplyBy(a)
        }}), h.CRS.EPSG900913 = h.extend({}, h.CRS.EPSG3857, {code: "EPSG:900913"}), h.CRS.EPSG4326 = h.extend({}, h.CRS, {code: "EPSG:4326", projection: h.Projection.LonLat, transformation: new h.Transformation(1 / 360, 0.5, -1 / 360, 0.5)}), h.Map = h.Class.extend({includes: h.Mixin.Events, options: {crs: h.CRS.EPSG3857, fadeAnimation: h.DomUtil.TRANSITION && !h.Browser.android23, trackResize: !0, markerZoomAnimation: h.DomUtil.TRANSITION && h.Browser.any3d}, initialize: function(a, i) {
            i = h.setOptions(this, i), this._initContainer(a), this._initLayout(), this._onResize = h.bind(this._onResize, this), this._initEvents(), i.maxBounds && this.setMaxBounds(i.maxBounds), i.center && i.zoom !== c && this.setView(h.latLng(i.center), i.zoom, {reset: !0}), this._handlers = [], this._layers = {}, this._zoomBoundLayers = {}, this._tileLayersNum = 0, this.callInitHooks(), this._addLayers(i.layers)
        }, setView: function(a, i) {
            return i = i === c ? this.getZoom() : i, this._resetView(h.latLng(a), this._limitZoom(i)), this
        }, setZoom: function(a, i) {
            return this._loaded ? this.setView(this.getCenter(), a, {zoom: i}) : (this._zoom = this._limitZoom(a), this)
        }, zoomIn: function(a, i) {
            return this.setZoom(this._zoom + (a || 1), i)
        }, zoomOut: function(a, i) {
            return this.setZoom(this._zoom - (a || 1), i)
        }, setZoomAround: function(m, u, l) {
            var v = this.getZoomScale(u), p = this.getSize().divideBy(2), k = m instanceof h.Point ? m : this.latLngToContainerPoint(m), q = k.subtract(p).multiplyBy(1 - 1 / v), o = this.containerPointToLatLng(p.add(q));
            return this.setView(o, u, {zoom: l})
        }, fitBounds: function(w, u) {
            u = u || {}, w = w.getBounds ? w.getBounds() : h.latLngBounds(w);
            var p = h.point(u.paddingTopLeft || u.padding || [0, 0]), m = h.point(u.paddingBottomRight || u.padding || [0, 0]), x = this.getBoundsZoom(w, !1, p.add(m)), v = m.subtract(p).divideBy(2), k = this.project(w.getSouthWest(), x), q = this.project(w.getNorthEast(), x), o = this.unproject(k.add(q).divideBy(2).add(v), x);
            return x = u && u.maxZoom ? Math.min(u.maxZoom, x) : x, this.setView(o, x, u)
        }, fitWorld: function(a) {
            return this.fitBounds([[-90, -180], [90, 180]], a)
        }, panTo: function(a, i) {
            return this.setView(a, this._zoom, {pan: i})
        }, panBy: function(a) {
            return this.fire("movestart"), this._rawPanBy(h.point(a)), this.fire("move"), this.fire("moveend")
        }, setMaxBounds: function(a) {
            return a = h.latLngBounds(a), this.options.maxBounds = a, a ? (this._loaded && this._panInsideMaxBounds(), this.on("moveend", this._panInsideMaxBounds, this)) : this.off("moveend", this._panInsideMaxBounds, this)
        }, panInsideBounds: function(k, l) {
            var a = this.getCenter(), m = this._limitCenter(a, this._zoom, k);
            return a.equals(m) ? this : this.panTo(m, l)
        }, addLayer: function(a) {
            var i = h.stamp(a);
            return this._layers[i] ? this : (this._layers[i] = a, !a.options || isNaN(a.options.maxZoom) && isNaN(a.options.minZoom) || (this._zoomBoundLayers[i] = a, this._updateZoomLevels()), this.options.zoomAnimation && h.TileLayer && a instanceof h.TileLayer && (this._tileLayersNum++, this._tileLayersToLoad++, a.on("load", this._onTileLayerLoad, this)), this._loaded && this._layerAdd(a), this)
        }, removeLayer: function(a) {
            var i = h.stamp(a);
            return this._layers[i] ? (this._loaded && a.onRemove(this), delete this._layers[i], this._loaded && this.fire("layerremove", {layer: a}), this._zoomBoundLayers[i] && (delete this._zoomBoundLayers[i], this._updateZoomLevels()), this.options.zoomAnimation && h.TileLayer && a instanceof h.TileLayer && (this._tileLayersNum--, this._tileLayersToLoad--, a.off("load", this._onTileLayerLoad, this)), this) : this
        }, hasLayer: function(a) {
            return a ? h.stamp(a) in this._layers : !1
        }, eachLayer: function(k, l) {
            for (var a in this._layers) {
                k.call(l, this._layers[a])
            }
            return this
        }, invalidateSize: function(m) {
            if (!this._loaded) {
                return this
            }
            m = h.extend({animate: !1, pan: !0}, m === !0 ? {animate: !0} : m);
            var p = this.getSize();
            this._sizeChanged = !0, this._initialCenter = null;
            var l = this.getSize(), q = p.divideBy(2).round(), o = l.divideBy(2).round(), k = q.subtract(o);
            return k.x || k.y ? (m.animate && m.pan ? this.panBy(k) : (m.pan && this._rawPanBy(k), this.fire("move"), m.debounceMoveend ? (clearTimeout(this._sizeTimer), this._sizeTimer = setTimeout(h.bind(this.fire, this, "moveend"), 200)) : this.fire("moveend")), this.fire("resize", {oldSize: p, newSize: l})) : this
        }, addHandler: function(k, l) {
            if (!l) {
                return this
            }
            var a = this[k] = new l(this);
            return this._handlers.push(a), this.options[k] && a.enable(), this
        }, remove: function() {
            this._loaded && this.fire("unload"), this._initEvents("off");
            try {
                delete this._container._leaflet
            } catch (a) {
                this._container._leaflet = c
            }
            return this._clearPanes(), this._clearControlPos && this._clearControlPos(), this._clearHandlers(), this
        }, getCenter: function() {
            return this._checkIfLoaded(), this._initialCenter && !this._moved() ? this._initialCenter : this.layerPointToLatLng(this._getCenterLayerPoint())
        }, getZoom: function() {
            return this._zoom
        }, getBounds: function() {
            var k = this.getPixelBounds(), l = this.unproject(k.getBottomLeft()), a = this.unproject(k.getTopRight());
            return new h.LatLngBounds(l, a)
        }, getMinZoom: function() {
            return this.options.minZoom === c ? this._layersMinZoom === c ? 0 : this._layersMinZoom : this.options.minZoom
        }, getMaxZoom: function() {
            return this.options.maxZoom === c ? this._layersMaxZoom === c ? 1 / 0 : this._layersMaxZoom : this.options.maxZoom
        }, getBoundsZoom: function(y, v, p) {
            y = h.latLngBounds(y);
            var m, z = this.getMinZoom() - (v ? 1 : 0), w = this.getMaxZoom(), k = this.getSize(), q = y.getNorthWest(), o = y.getSouthEast(), x = !0;
            p = h.point(p || [0, 0]);
            do {
                z++, m = this.project(o, z).subtract(this.project(q, z)).add(p), x = v ? m.x < k.x || m.y < k.y : k.contains(m)
            } while (x && w >= z);
            return x && v ? null : v ? z : z - 1
        }, getSize: function() {
            return(!this._size || this._sizeChanged) && (this._size = new h.Point(this._container.clientWidth, this._container.clientHeight), this._sizeChanged = !1), this._size.clone()
        }, getPixelBounds: function() {
            var a = this._getTopLeftPoint();
            return new h.Bounds(a, a.add(this.getSize()))
        }, getPixelOrigin: function() {
            return this._checkIfLoaded(), this._initialTopLeftPoint
        }, getPanes: function() {
            return this._panes
        }, getContainer: function() {
            return this._container
        }, getZoomScale: function(a) {
            var i = this.options.crs;
            return i.scale(a) / i.scale(this._zoom)
        }, getScaleZoom: function(a) {
            return this._zoom + Math.log(a) / Math.LN2
        }, project: function(a, i) {
            return i = i === c ? this._zoom : i, this.options.crs.latLngToPoint(h.latLng(a), i)
        }, unproject: function(a, i) {
            return i = i === c ? this._zoom : i, this.options.crs.pointToLatLng(h.point(a), i)
        }, layerPointToLatLng: function(a) {
            var i = h.point(a).add(this.getPixelOrigin());
            return this.unproject(i)
        }, latLngToLayerPoint: function(a) {
            var i = this.project(h.latLng(a))._round();
            return i._subtract(this.getPixelOrigin())
        }, containerPointToLayerPoint: function(a) {
            return h.point(a).subtract(this._getMapPanePos())
        }, layerPointToContainerPoint: function(a) {
            return h.point(a).add(this._getMapPanePos())
        }, containerPointToLatLng: function(a) {
            var i = this.containerPointToLayerPoint(h.point(a));
            return this.layerPointToLatLng(i)
        }, latLngToContainerPoint: function(a) {
            return this.layerPointToContainerPoint(this.latLngToLayerPoint(h.latLng(a)))
        }, mouseEventToContainerPoint: function(a) {
            return h.DomEvent.getMousePosition(a, this._container)
        }, mouseEventToLayerPoint: function(a) {
            return this.containerPointToLayerPoint(this.mouseEventToContainerPoint(a))
        }, mouseEventToLatLng: function(a) {
            return this.layerPointToLatLng(this.mouseEventToLayerPoint(a))
        }, _initContainer: function(a) {
            var i = this._container = h.DomUtil.get(a);
            if (!i) {
                throw new Error("Map container not found.")
            }
            if (i._leaflet) {
                throw new Error("Map container is already initialized.")
            }
            i._leaflet = !0
        }, _initLayout: function() {
            var a = this._container;
            h.DomUtil.addClass(a, "leaflet-container" + (h.Browser.touch ? " leaflet-touch" : "") + (h.Browser.retina ? " leaflet-retina" : "") + (h.Browser.ielt9 ? " leaflet-oldie" : "") + (this.options.fadeAnimation ? " leaflet-fade-anim" : ""));
            var i = h.DomUtil.getStyle(a, "position");
            "absolute" !== i && "relative" !== i && "fixed" !== i && (a.style.position = "relative"), this._initPanes(), this._initControlPos && this._initControlPos()
        }, _initPanes: function() {
            var a = this._panes = {};
            this._mapPane = a.mapPane = this._createPane("leaflet-map-pane", this._container), this._tilePane = a.tilePane = this._createPane("leaflet-tile-pane", this._mapPane), a.objectsPane = this._createPane("leaflet-objects-pane", this._mapPane), a.shadowPane = this._createPane("leaflet-shadow-pane"), a.overlayPane = this._createPane("leaflet-overlay-pane"), a.markerPane = this._createPane("leaflet-marker-pane"), a.popupPane = this._createPane("leaflet-popup-pane");
            var i = " leaflet-zoom-hide";
            this.options.markerZoomAnimation || (h.DomUtil.addClass(a.markerPane, i), h.DomUtil.addClass(a.shadowPane, i), h.DomUtil.addClass(a.popupPane, i))
        }, _createPane: function(a, i) {
            return h.DomUtil.create("div", a, i || this._panes.objectsPane)
        }, _clearPanes: function() {
            this._container.removeChild(this._mapPane)
        }, _addLayers: function(k) {
            k = k ? h.Util.isArray(k) ? k : [k] : [];
            for (var l = 0, a = k.length; a > l; l++) {
                this.addLayer(k[l])
            }
        }, _resetView: function(m, p, l, q) {
            var o = this._zoom !== p;
            q || (this.fire("movestart"), o && this.fire("zoomstart")), this._zoom = p, this._initialCenter = m, this._initialTopLeftPoint = this._getNewTopLeftPoint(m), l ? this._initialTopLeftPoint._add(this._getMapPanePos()) : h.DomUtil.setPosition(this._mapPane, new h.Point(0, 0)), this._tileLayersToLoad = this._tileLayersNum;
            var k = !this._loaded;
            this._loaded = !0, this.fire("viewreset", {hard: !l}), k && (this.fire("load"), this.eachLayer(this._layerAdd, this)), this.fire("move"), (o || q) && this.fire("zoomend"), this.fire("moveend", {hard: !l})
        }, _rawPanBy: function(a) {
            h.DomUtil.setPosition(this._mapPane, this._getMapPanePos().subtract(a))
        }, _getZoomSpan: function() {
            return this.getMaxZoom() - this.getMinZoom()
        }, _updateZoomLevels: function() {
            var a, k = 1 / 0, m = -1 / 0, l = this._getZoomSpan();
            for (a in this._zoomBoundLayers) {
                var i = this._zoomBoundLayers[a];
                isNaN(i.options.minZoom) || (k = Math.min(k, i.options.minZoom)), isNaN(i.options.maxZoom) || (m = Math.max(m, i.options.maxZoom))
            }
            a === c ? this._layersMaxZoom = this._layersMinZoom = c : (this._layersMaxZoom = m, this._layersMinZoom = k), l !== this._getZoomSpan() && this.fire("zoomlevelschange")
        }, _panInsideMaxBounds: function() {
            this.panInsideBounds(this.options.maxBounds)
        }, _checkIfLoaded: function() {
            if (!this._loaded) {
                throw new Error("Set map center and zoom first.")
            }
        }, _initEvents: function(l) {
            if (h.DomEvent) {
                l = l || "on", h.DomEvent[l](this._container, "click", this._onMouseClick, this);
                var a, m, k = ["dblclick", "mousedown", "mouseup", "mouseenter", "mouseleave", "mousemove", "contextmenu"];
                for (a = 0, m = k.length; m > a; a++) {
                    h.DomEvent[l](this._container, k[a], this._fireMouseEvent, this)
                }
                this.options.trackResize && h.DomEvent[l](d, "resize", this._onResize, this)
            }
        }, _onResize: function() {
            h.Util.cancelAnimFrame(this._resizeRequest), this._resizeRequest = h.Util.requestAnimFrame(function() {
                this.invalidateSize({debounceMoveend: !0})
            }, this, !1, this._container)
        }, _onMouseClick: function(a) {
            !this._loaded || !a._simulated && (this.dragging && this.dragging.moved() || this.boxZoom && this.boxZoom.moved()) || h.DomEvent._skipped(a) || (this.fire("preclick"), this._fireMouseEvent(a))
        }, _fireMouseEvent: function(k) {
            if (this._loaded && !h.DomEvent._skipped(k)) {
                var m = k.type;
                if (m = "mouseenter" === m ? "mouseover" : "mouseleave" === m ? "mouseout" : m, this.hasEventListeners(m)) {
                    "contextmenu" === m && h.DomEvent.preventDefault(k);
                    var a = this.mouseEventToContainerPoint(k), o = this.containerPointToLayerPoint(a), l = this.layerPointToLatLng(o);
                    this.fire(m, {latlng: l, layerPoint: o, containerPoint: a, originalEvent: k})
                }
            }
        }, _onTileLayerLoad: function() {
            this._tileLayersToLoad--, this._tileLayersNum && !this._tileLayersToLoad && this.fire("tilelayersload")
        }, _clearHandlers: function() {
            for (var a = 0, i = this._handlers.length; i > a; a++) {
                this._handlers[a].disable()
            }
        }, whenReady: function(a, i) {
            return this._loaded ? a.call(i || this, this) : this.on("load", a, i), this
        }, _layerAdd: function(a) {
            a.onAdd(this);
            this.fire("layeradd", {layer: a})
        }, _getMapPanePos: function() {
            return h.DomUtil.getPosition(this._mapPane)
        }, _moved: function() {
            var a = this._getMapPanePos();
            return a && !a.equals([0, 0])
        }, _getTopLeftPoint: function() {
            return this.getPixelOrigin().subtract(this._getMapPanePos())
        }, _getNewTopLeftPoint: function(k, l) {
            var a = this.getSize()._divideBy(2);
            return this.project(k, l)._subtract(a)._round()
        }, _latLngToNewLayerPoint: function(k, l, a) {
            var m = this._getNewTopLeftPoint(a, l).add(this._getMapPanePos());
            return this.project(k, l)._subtract(m)
        }, _getCenterLayerPoint: function() {
            return this.containerPointToLayerPoint(this.getSize()._divideBy(2))
        }, _getCenterOffset: function(a) {
            return this.latLngToLayerPoint(a).subtract(this._getCenterLayerPoint())
        }, _limitCenter: function(m, q, l) {
            if (!l) {
                return m
            }
            var u = this.project(m, q), o = this.getSize().divideBy(2), k = new h.Bounds(u.subtract(o), u.add(o)), p = this._getBoundsOffset(k, l, q);
            return this.unproject(u.add(p), q)
        }, _limitOffset: function(k, l) {
            if (!l) {
                return k
            }
            var a = this.getPixelBounds(), m = new h.Bounds(a.min.add(k), a.max.add(k));
            return k.add(this._getBoundsOffset(m, l))
        }, _getBoundsOffset: function(m, q, l) {
            var u = this.project(q.getNorthWest(), l).subtract(m.min), o = this.project(q.getSouthEast(), l).subtract(m.max), k = this._rebound(u.x, -o.x), p = this._rebound(u.y, -o.y);
            return new h.Point(k, p)
        }, _rebound: function(a, i) {
            return a + i > 0 ? Math.round(a - i) / 2 : Math.max(0, Math.ceil(a)) - Math.max(0, Math.floor(i))
        }, _limitZoom: function(k) {
            var l = this.getMinZoom(), a = this.getMaxZoom();
            return Math.max(l, Math.min(a, k))
        }}), h.map = function(a, i) {
        return new h.Map(a, i)
    }, h.Projection.Mercator = {MAX_LATITUDE: 85.0840591556, R_MINOR: 6356752.314245179, R_MAJOR: 6378137, project: function(A) {
            var v = h.LatLng.DEG_TO_RAD, p = this.MAX_LATITUDE, m = Math.max(Math.min(p, A.lat), -p), B = this.R_MAJOR, y = this.R_MINOR, k = A.lng * v * B, q = m * v, o = y / B, z = Math.sqrt(1 - o * o), x = z * Math.sin(q);
            x = Math.pow((1 - x) / (1 + x), 0.5 * z);
            var w = Math.tan(0.5 * (0.5 * Math.PI - q)) / x;
            return q = -B * Math.log(w), new h.Point(k, q)
        }, unproject: function(D) {
            for (var x, v = h.LatLng.RAD_TO_DEG, o = this.R_MAJOR, E = this.R_MINOR, A = D.x * v / o, k = E / o, w = Math.sqrt(1 - k * k), q = Math.exp(-D.y / o), C = Math.PI / 2 - 2 * Math.atan(q), z = 15, y = 1e-7, m = z, B = 0.1; Math.abs(B) > y && --m > 0; ) {
                x = w * Math.sin(C), B = Math.PI / 2 - 2 * Math.atan(q * Math.pow((1 - x) / (1 + x), 0.5 * w)) - C, C += B
            }
            return new h.LatLng(C * v, A)
        }}, h.CRS.EPSG3395 = h.extend({}, h.CRS, {code: "EPSG:3395", projection: h.Projection.Mercator, transformation: function() {
            var k = h.Projection.Mercator, l = k.R_MAJOR, a = 0.5 / (Math.PI * l);
            return new h.Transformation(a, 0.5, -a, 0.5)
        }()}), h.TileLayer = h.Class.extend({includes: h.Mixin.Events, options: {minZoom: 5, maxZoom: 18, tileSize: 256, subdomains: "abc", errorTileUrl: "", attribution: "Map Tiles,Data &copy;MapmyIndia", zoomOffset: 0, opacity: 1, unloadInvisibleTiles: h.Browser.mobile, updateWhenIdle: h.Browser.mobile, }, initialize: function(k, l) {
            l = h.setOptions(this, l), l.detectRetina && h.Browser.retina && l.maxZoom > 0 && (l.tileSize = Math.floor(l.tileSize / 2), l.zoomOffset++, l.minZoom > 0 && l.minZoom--, this.options.maxZoom--), l.bounds && (l.bounds = h.latLngBounds(l.bounds)), this._url = k;
            var a = this.options.subdomains;
            "string" == typeof a && (this.options.subdomains = a.split(""))
        }, onAdd: function(a) {
            this._map = a, this._animated = a._zoomAnimated, this._initContainer(), a.on({viewreset: this._reset, moveend: this._update}, this), this._animated && a.on({zoomanim: this._animateZoom, zoomend: this._endZoomAnim}, this), this.options.updateWhenIdle || (this._limitedUpdate = h.Util.limitExecByInterval(this._update, 150, this), a.on("move", this._limitedUpdate, this)), this._reset(), this._update()
        }, addTo: function(a) {
            return a.addLayer(this), this
        }, onRemove: function(a) {
            this._container.parentNode.removeChild(this._container), a.off({viewreset: this._reset, moveend: this._update}, this), this._animated && a.off({zoomanim: this._animateZoom, zoomend: this._endZoomAnim}, this), this.options.updateWhenIdle || a.off("move", this._limitedUpdate, this), this._container = null, this._map = null
        }, bringToFront: function() {
            var a = this._map._panes.tilePane;
            return this._container && (a.appendChild(this._container), this._setAutoZIndex(a, Math.max)), this
        }, bringToBack: function() {
            var a = this._map._panes.tilePane;
            return this._container && (a.insertBefore(this._container, a.firstChild), this._setAutoZIndex(a, Math.min)), this
        }, getAttribution: function() {
            return this.options.attribution
        }, getContainer: function() {
            return this._container
        }, setOpacity: function(a) {
            return this.options.opacity = a, this._map && this._updateOpacity(), this
        }, setZIndex: function(a) {
            return this.options.zIndex = a, this._updateZIndex(), this
        }, setUrl: function(a, i) {
            return this._url = a, i || this.redraw(), this
        }, redraw: function() {
            return this._map && (this._reset({hard: !0}), this._update()), this
        }, _updateZIndex: function() {
            this._container && this.options.zIndex !== c && (this._container.style.zIndex = this.options.zIndex)
        }, _setAutoZIndex: function(m, q) {
            var l, u, r, p = m.children, k = -q(1 / 0, -1 / 0);
            for (u = 0, r = p.length; r > u; u++) {
                p[u] !== this._container && (l = parseInt(p[u].style.zIndex, 10), isNaN(l) || (k = q(k, l)))
            }
            this.options.zIndex = this._container.style.zIndex = (isFinite(k) ? k : 0) + q(1, -1)
        }, _updateOpacity: function() {
            var a, i = this._tiles;
            if (h.Browser.ielt9) {
                for (a in i) {
                    h.DomUtil.setOpacity(i[a], this.options.opacity)
                }
            } else {
                h.DomUtil.setOpacity(this._container, this.options.opacity)
            }
        }, _initContainer: function() {
            var a = this._map._panes.tilePane;
            if (!this._container) {
                if (this._container = h.DomUtil.create("div", "leaflet-layer"), this._updateZIndex(), this._animated) {
                    var i = "leaflet-tile-container";
                    this._bgBuffer = h.DomUtil.create("div", i, this._container), this._tileContainer = h.DomUtil.create("div", i, this._container)
                } else {
                    this._tileContainer = this._container
                }
                a.appendChild(this._container), this.options.opacity < 1 && this._updateOpacity()
            }
        }, _reset: function(a) {
            for (var i in this._tiles) {
                this.fire("tileunload", {tile: this._tiles[i]})
            }
            this._tiles = {}, this._tilesToLoad = 0, this.options.reuseTiles && (this._unusedTiles = []), this._tileContainer.innerHTML = "", this._animated && a && a.hard && this._clearBgBuffer(), this._initContainer()
        }, _getTileSize: function() {
            var k = this._map, l = k.getZoom() + this.options.zoomOffset, a = this.options.maxNativeZoom, m = this.options.tileSize;
            return a && l > a && (m = Math.round(k.getZoomScale(l) / k.getZoomScale(a) * m)), m
        }, _update: function() {
            if (this._map) {
                var k = this._map, m = k.getPixelBounds(), a = k.getZoom(), o = this._getTileSize();
                if (!(a > this.options.maxZoom || a < this.options.minZoom)) {
                    var l = h.bounds(m.min.divideBy(o)._floor(), m.max.divideBy(o)._floor());
                    this._addTilesFromCenterOut(l), (this.options.unloadInvisibleTiles || this.options.reuseTiles) && this._removeOtherTiles(l)
                }
            }
        }, _addTilesFromCenterOut: function(o) {
            var m, v, q, k = [], u = o.getCenter();
            for (m = o.min.y; m <= o.max.y; m++) {
                for (v = o.min.x; v <= o.max.x; v++) {
                    q = new h.Point(v, m), this._tileShouldBeLoaded(q) && k.push(q)
                }
            }
            var p = k.length;
            if (0 !== p) {
                k.sort(function(a, i) {
                    return a.distanceTo(u) - i.distanceTo(u)
                });
                var e = g.createDocumentFragment();
                for (this._tilesToLoad || this.fire("loading"), this._tilesToLoad += p, v = 0; p > v; v++) {
                    this._addTile(k[v], e)
                }
                this._tileContainer.appendChild(e)
            }
        }, _tileShouldBeLoaded: function(m) {
            if (m.x + ":" + m.y in this._tiles) {
                return !1
            }
            var u = this.options;
            if (!u.continuousWorld) {
                var l = this._getWrapTileNum();
                if (u.noWrap && (m.x < 0 || m.x >= l.x) || m.y < 0 || m.y >= l.y) {
                    return !1
                }
            }
            if (u.bounds) {
                var w = u.tileSize, v = m.multiplyBy(w), p = v.add([w, w]), k = this._map.unproject(v), q = this._map.unproject(p);
                if (u.continuousWorld || u.noWrap || (k = k.wrap(), q = q.wrap()), !u.bounds.intersects([k, q])) {
                    return !1
                }
            }
            return !0
        }, _removeOtherTiles: function(k) {
            var l, a, p, m;
            for (m in this._tiles) {
                l = m.split(":"), a = parseInt(l[0], 10), p = parseInt(l[1], 10), (a < k.min.x || a > k.max.x || p < k.min.y || p > k.max.y) && this._removeTile(m)
            }
        }, _removeTile: function(a) {
            var i = this._tiles[a];
            this.fire("tileunload", {tile: i, url: i.src}), this.options.reuseTiles ? (h.DomUtil.removeClass(i, "leaflet-tile-loaded"), this._unusedTiles.push(i)) : i.parentNode === this._tileContainer && this._tileContainer.removeChild(i), h.Browser.android || (i.onload = null, i.src = h.Util.emptyImageUrl), delete this._tiles[a]
        }, _addTile: function(k, l) {
            var a = this._getTilePos(k), m = this._getTile();
            h.DomUtil.setPosition(m, a, h.Browser.chrome), this._tiles[k.x + ":" + k.y] = m, this._loadTile(m, k), m.parentNode !== this._tileContainer && l.appendChild(m)
        }, _getZoomForUrl: function() {
            var a = this.options, i = this._map.getZoom();
            return a.zoomReverse && (i = a.maxZoom - i), i += a.zoomOffset, a.maxNativeZoom ? Math.min(i, a.maxNativeZoom) : i
        }, _getTilePos: function(k) {
            var l = this._map.getPixelOrigin(), a = this._getTileSize();
            return k.multiplyBy(a).subtract(l)
        }, getTileUrl: function(e) {
            var a = h.ttl(e.x, e.y, e.z), l = Math.pow(2, 20 - e.z), k = h.Browser.retina ? "1" : "0";
            var i = h.Util.template(this._url, h.extend({z: l, x: a.y, y: a.x, s: k, }, this.options));
            return i
        }, _getWrapTileNum: function() {
            var a = this._map.options.crs, i = a.getSize(this._map.getZoom());
            return i.divideBy(this._getTileSize())._floor()
        }, _adjustTilePoint: function(a) {
            var i = this._getWrapTileNum();
            this.options.continuousWorld || this.options.noWrap || (a.x = (a.x % i.x + i.x) % i.x), this.options.tms && (a.y = i.y - a.y - 1), a.z = this._getZoomForUrl()
        }, _getSubdomain: function(a) {
            var i = Math.abs(a.x + a.y) % this.options.subdomains.length;
            return this.options.subdomains[i]
        }, _getTile: function() {
            if (this.options.reuseTiles && this._unusedTiles.length > 0) {
                var a = this._unusedTiles.pop();
                return this._resetTile(a), a
            }
            return this._createTile()
        }, _resetTile: function() {
        }, _createTile: function() {
            var a = h.DomUtil.create("img", "leaflet-tile");
            return a.style.width = a.style.height = this._getTileSize() + "px", a.galleryimg = "no", a.onselectstart = a.onmousemove = h.Util.falseFn, h.Browser.ielt9 && this.options.opacity !== c && h.DomUtil.setOpacity(a, this.options.opacity), h.Browser.mobileWebkit3d && (a.style.WebkitBackfaceVisibility = "hidden"), a
        }, _loadTile: function(a, i) {
            a._layer = this, a.onload = this._tileOnLoad, a.onerror = this._tileOnError, this._adjustTilePoint(i), a.src = this.getTileUrl(i), this.fire("tileloadstart", {tile: a, url: a.src})
        }, _tileLoaded: function() {
            this._tilesToLoad--, this._animated && h.DomUtil.addClass(this._tileContainer, "leaflet-zoom-animated"), this._tilesToLoad || (this.fire("load"), this._animated && (clearTimeout(this._clearBgBufferTimer), this._clearBgBufferTimer = setTimeout(h.bind(this._clearBgBuffer, this), 500)))
        }, _tileOnLoad: function() {
            var a = this._layer;
            this.src !== h.Util.emptyImageUrl && (h.DomUtil.addClass(this, "leaflet-tile-loaded"), a.fire("tileload", {tile: this, url: this.src})), a._tileLoaded()
        }, _tileOnError: function() {
            var a = this._layer;
            a.fire("tileerror", {tile: this, url: this.src});
            var i = a.options.errorTileUrl;
            i && (this.src = i), a._tileLoaded()
        }}), h.tileLayer = function(a, i) {
        return new h.TileLayer(a, i)
    }, h.TileLayer.WMS = h.TileLayer.extend({defaultWmsParams: {service: "WMS", request: "GetMap", version: "1.1.1", layers: "", styles: "", format: "image/jpeg", transparent: !1}, initialize: function(k, m) {
            this._url = k;
            var a = h.extend({}, this.defaultWmsParams), o = m.tileSize || this.options.tileSize;
            a.width = a.height = m.detectRetina && h.Browser.retina ? 2 * o : o;
            for (var l in m) {
                this.options.hasOwnProperty(l) || "crs" === l || (a[l] = m[l])
            }
            this.wmsParams = a, h.setOptions(this, m)
        }, onAdd: function(a) {
            this._crs = this.options.crs || a.options.crs, this._wmsVersion = parseFloat(this.wmsParams.version);
            var i = this._wmsVersion >= 1.3 ? "crs" : "srs";
            this.wmsParams[i] = this._crs.code, h.TileLayer.prototype.onAdd.call(this, a)
        }, getTileUrl: function(w) {
            var u = this._map, p = this.options.tileSize, m = w.multiplyBy(p), x = m.add([p, p]), v = this._crs.project(u.unproject(m, w.z)), k = this._crs.project(u.unproject(x, w.z)), q = this._wmsVersion >= 1.3 && this._crs === h.CRS.EPSG4326 ? [k.y, v.x, v.y, k.x].join(",") : [v.x, k.y, k.x, v.y].join(","), o = h.Util.template(this._url, {s: this._getSubdomain(w)});
            return o + h.Util.getParamString(this.wmsParams, o, !0) + "&BBOX=" + q
        }, setParams: function(a, i) {
            return h.extend(this.wmsParams, a), i || this.redraw(), this
        }}), h.tileLayer.wms = function(a, i) {
        return new h.TileLayer.WMS(a, i)
    }, h.TileLayer.Canvas = h.TileLayer.extend({options: {async: !1}, initialize: function(a) {
            h.setOptions(this, a)
        }, redraw: function() {
            this._map && (this._reset({hard: !0}), this._update());
            for (var a in this._tiles) {
                this._redrawTile(this._tiles[a])
            }
            return this
        }, _redrawTile: function(a) {
            this.drawTile(a, a._tilePoint, this._map._zoom)
        }, _createTile: function() {
            var a = h.DomUtil.create("canvas", "leaflet-tile");
            return a.width = a.height = this.options.tileSize, a.onselectstart = a.onmousemove = h.Util.falseFn, a
        }, _loadTile: function(a, i) {
            a._layer = this, a._tilePoint = i, this._redrawTile(a), this.options.async || this.tileDrawn(a)
        }, drawTile: function() {
        }, tileDrawn: function(a) {
            this._tileOnLoad.call(a)
        }}), h.tileLayer.canvas = function(a) {
        return new h.TileLayer.Canvas(a)
    }, h.ImageOverlay = h.Class.extend({includes: h.Mixin.Events, options: {opacity: 1}, initialize: function(k, l, a) {
            this._url = k, this._bounds = h.latLngBounds(l), h.setOptions(this, a)
        }, onAdd: function(a) {
            this._map = a, this._image || this._initImage(), a._panes.overlayPane.appendChild(this._image), a.on("viewreset", this._reset, this), a.options.zoomAnimation && h.Browser.any3d && a.on("zoomanim", this._animateZoom, this), this._reset()
        }, onRemove: function(a) {
            a.getPanes().overlayPane.removeChild(this._image), a.off("viewreset", this._reset, this), a.options.zoomAnimation && a.off("zoomanim", this._animateZoom, this)
        }, addTo: function(a) {
            return a.addLayer(this), this
        }, setOpacity: function(a) {
            return this.options.opacity = a, this._updateOpacity(), this
        }, bringToFront: function() {
            return this._image && this._map._panes.overlayPane.appendChild(this._image), this
        }, bringToBack: function() {
            var a = this._map._panes.overlayPane;
            return this._image && a.insertBefore(this._image, a.firstChild), this
        }, setUrl: function(a) {
            this._url = a, this._image.src = this._url
        }, getAttribution: function() {
            return this.options.attribution
        }, _initImage: function() {
            this._image = h.DomUtil.create("img", "leaflet-image-layer"), this._map.options.zoomAnimation && h.Browser.any3d ? h.DomUtil.addClass(this._image, "leaflet-zoom-animated") : h.DomUtil.addClass(this._image, "leaflet-zoom-hide"), this._updateOpacity(), h.extend(this._image, {galleryimg: "no", onselectstart: h.Util.falseFn, onmousemove: h.Util.falseFn, onload: h.bind(this._onImageLoad, this), src: this._url})
        }, _animateZoom: function(w) {
            var u = this._map, p = this._image, m = u.getZoomScale(w.zoom), x = this._bounds.getNorthWest(), v = this._bounds.getSouthEast(), k = u._latLngToNewLayerPoint(x, w.zoom, w.center), q = u._latLngToNewLayerPoint(v, w.zoom, w.center)._subtract(k), o = k._add(q._multiplyBy(0.5 * (1 - 1 / m)));
            p.style[h.DomUtil.TRANSFORM] = h.DomUtil.getTranslateString(o) + " scale(" + m + ") "
        }, _reset: function() {
            var k = this._image, l = this._map.latLngToLayerPoint(this._bounds.getNorthWest()), a = this._map.latLngToLayerPoint(this._bounds.getSouthEast())._subtract(l);
            h.DomUtil.setPosition(k, l), k.style.width = a.x + "px", k.style.height = a.y + "px"
        }, _onImageLoad: function() {
            this.fire("load")
        }, _updateOpacity: function() {
            h.DomUtil.setOpacity(this._image, this.options.opacity)
        }}), h.imageOverlay = function(k, l, a) {
        return new h.ImageOverlay(k, l, a)
    }, h.Icon = h.Class.extend({options: {className: ""}, initialize: function(a) {
            h.setOptions(this, a)
        }, createIcon: function(a) {
            return this._createIcon("icon", a)
        }, createShadow: function(a) {
            return this._createIcon("shadow", a)
        }, _createIcon: function(k, l) {
            var a = this._getIconUrl(k);
            if (!a) {
                if ("icon" === k) {
                    throw new Error("iconUrl not set in Icon options (see the docs).")
                }
                return null
            }
            var m;
            return m = l && "IMG" === l.tagName ? this._createImg(a, l) : this._createImg(a), this._setIconStyles(m, k), m
        }, _setIconStyles: function(k, m) {
            var a, o = this.options, l = h.point(o[m + "Size"]);
            a = h.point("shadow" === m ? o.shadowAnchor || o.iconAnchor : o.iconAnchor), !a && l && (a = l.divideBy(2, !0)), k.className = "leaflet-marker-" + m + " " + o.className, a && (k.style.marginLeft = -a.x + "px", k.style.marginTop = -a.y + "px"), l && (k.style.width = l.x + "px", k.style.height = l.y + "px")
        }, _createImg: function(e, a) {
            return a = a || g.createElement("img"), a.src = e, a
        }, _getIconUrl: function(a) {
            return h.Browser.retina && this.options[a + "RetinaUrl"] ? this.options[a + "RetinaUrl"] : this.options[a + "Url"]
        }}), h.icon = function(a) {
        return new h.Icon(a)
    }, h.Icon.Default = h.Icon.extend({options: {iconSize: [25, 41], iconAnchor: [12, 41], popupAnchor: [1, -34], shadowSize: [41, 41]}, _getIconUrl: function(k) {
            var l = k + "Url";
            if (this.options[l]) {
                return this.options[l]
            }
            h.Browser.retina && "icon" === k && (k += "-2x");
            var a = h.Icon.Default.imagePath;
            if (!a) {
                throw new Error("Couldn't autodetect L.Icon.Default.imagePath, set it manually.")
            }
            return a + "/marker-" + k + ".png"
        }}), h.Icon.Default.imagePath = function() {
        var l, k, u, q, m, e = g.getElementsByTagName("script"), p = /[\/^]leaflet[\-\._]?([\w\-\._]*)\.js\??/;
        for (l = 0, k = e.length; k > l; l++) {
            if (u = e[l].src, q = u.match(p)) {
                return m = u.split(p)[0], (m ? m + "/" : "") + "images"
            }
        }
    }(), h.Marker = h.Class.extend({includes: h.Mixin.Events, options: {icon: new h.Icon.Default, title: "", alt: "", clickable: !0, draggable: !1, keyboard: !0, zIndexOffset: 0, opacity: 1, riseOnHover: !1, riseOffset: 250}, initialize: function(a, i) {
            h.setOptions(this, i), this._latlng = h.latLng(a)
        }, onAdd: function(a) {
            this._map = a, a.on("viewreset", this.update, this), this._initIcon(), this.update(), this.fire("add"), a.options.zoomAnimation && a.options.markerZoomAnimation && a.on("zoomanim", this._animateZoom, this)
        }, addTo: function(a) {
            return a.addLayer(this), this
        }, onRemove: function(a) {
            this.dragging && this.dragging.disable(), this._removeIcon(), this._removeShadow(), this.fire("remove"), a.off({viewreset: this.update, zoomanim: this._animateZoom}, this), this._map = null
        }, getLatLng: function() {
            return this._latlng
        }, setLatLng: function(a) {
            return this._latlng = h.latLng(a), this.update(), this.fire("move", {latlng: this._latlng})
        }, setZIndexOffset: function(a) {
            return this.options.zIndexOffset = a, this.update(), this
        }, setIcon: function(a) {
            return this.options.icon = a, this._map && (this._initIcon(), this.update()), this._popup && this.bindPopup(this._popup), this
        }, update: function() {
            if (this._icon) {
                var a = this._map.latLngToLayerPoint(this._latlng).round();
                this._setPos(a)
            }
            return this
        }, _initIcon: function() {
            var w = this.options, u = this._map, p = u.options.zoomAnimation && u.options.markerZoomAnimation, m = p ? "leaflet-zoom-animated" : "leaflet-zoom-hide", x = w.icon.createIcon(this._icon), v = !1;
            x !== this._icon && (this._icon && this._removeIcon(), v = !0, w.title && (x.title = w.title), w.alt && (x.alt = w.alt)), h.DomUtil.addClass(x, m), w.keyboard && (x.tabIndex = "0"), this._icon = x, this._initInteraction(), w.riseOnHover && h.DomEvent.on(x, "mouseover", this._bringToFront, this).on(x, "mouseout", this._resetZIndex, this);
            var k = w.icon.createShadow(this._shadow), q = !1;
            k !== this._shadow && (this._removeShadow(), q = !0), k && h.DomUtil.addClass(k, m), this._shadow = k, w.opacity < 1 && this._updateOpacity();
            var o = this._map._panes;
            v && o.markerPane.appendChild(this._icon), k && q && o.shadowPane.appendChild(this._shadow)
        }, _removeIcon: function() {
            this.options.riseOnHover && h.DomEvent.off(this._icon, "mouseover", this._bringToFront).off(this._icon, "mouseout", this._resetZIndex), this._map._panes.markerPane.removeChild(this._icon), this._icon = null
        }, _removeShadow: function() {
            this._shadow && this._map._panes.shadowPane.removeChild(this._shadow), this._shadow = null
        }, _setPos: function(a) {
            h.DomUtil.setPosition(this._icon, a), this._shadow && h.DomUtil.setPosition(this._shadow, a), this._zIndex = a.y + this.options.zIndexOffset, this._resetZIndex()
        }, _updateZIndex: function(a) {
            this._icon.style.zIndex = this._zIndex + a
        }, _animateZoom: function(a) {
            var i = this._map._latLngToNewLayerPoint(this._latlng, a.zoom, a.center).round();
            this._setPos(i)
        }, _initInteraction: function() {
            if (this.options.clickable) {
                var k = this._icon, l = ["dblclick", "mousedown", "mouseover", "mouseout", "contextmenu"];
                h.DomUtil.addClass(k, "leaflet-clickable"), h.DomEvent.on(k, "click", this._onMouseClick, this), h.DomEvent.on(k, "keypress", this._onKeyPress, this);
                for (var a = 0; a < l.length; a++) {
                    h.DomEvent.on(k, l[a], this._fireMouseEvent, this)
                }
                h.Handler.MarkerDrag && (this.dragging = new h.Handler.MarkerDrag(this), this.options.draggable && this.dragging.enable())
            }
        }, _onMouseClick: function(a) {
            var i = this.dragging && this.dragging.moved();
            (this.hasEventListeners(a.type) || i) && h.DomEvent.stopPropagation(a), i || (this.dragging && this.dragging._enabled || !this._map.dragging || !this._map.dragging.moved()) && this.fire(a.type, {originalEvent: a, latlng: this._latlng})
        }, _onKeyPress: function(a) {
            13 === a.keyCode && this.fire("click", {originalEvent: a, latlng: this._latlng})
        }, _fireMouseEvent: function(a) {
            this.fire(a.type, {originalEvent: a, latlng: this._latlng}), "contextmenu" === a.type && this.hasEventListeners(a.type) && h.DomEvent.preventDefault(a), "mousedown" !== a.type ? h.DomEvent.stopPropagation(a) : h.DomEvent.preventDefault(a)
        }, setOpacity: function(a) {
            return this.options.opacity = a, this._map && this._updateOpacity(), this
        }, _updateOpacity: function() {
            h.DomUtil.setOpacity(this._icon, this.options.opacity), this._shadow && h.DomUtil.setOpacity(this._shadow, this.options.opacity)
        }, _bringToFront: function() {
            this._updateZIndex(this.options.riseOffset)
        }, _resetZIndex: function() {
            this._updateZIndex(0)
        }}), h.marker = function(a, i) {
        return new h.Marker(a, i)
    }, h.DivIcon = h.Icon.extend({options: {iconSize: [12, 12], className: "leaflet-div-icon", html: !1}, createIcon: function(e) {
            var a = e && "DIV" === e.tagName ? e : g.createElement("div"), k = this.options;
            return a.innerHTML = k.html !== !1 ? k.html : "", k.bgPos && (a.style.backgroundPosition = -k.bgPos.x + "px " + -k.bgPos.y + "px"), this._setIconStyles(a, "icon"), a
        }, createShadow: function() {
            return null
        }}), h.divIcon = function(a) {
        return new h.DivIcon(a)
    }, h.Map.mergeOptions({closePopupOnClick: !0}), h.Popup = h.Class.extend({includes: h.Mixin.Events, options: {minWidth: 50, maxWidth: 300, autoPan: !0, closeButton: !0, offset: [0, 7], autoPanPadding: [5, 5], keepInView: !1, className: "", zoomAnimation: !0}, initialize: function(a, i) {
            h.setOptions(this, a), this._source = i, this._animated = h.Browser.any3d && this.options.zoomAnimation, this._isOpen = !1
        }, onAdd: function(a) {
            this._map = a, this._container || this._initLayout();
            var i = a.options.fadeAnimation;
            i && h.DomUtil.setOpacity(this._container, 0), a._panes.popupPane.appendChild(this._container), a.on(this._getEvents(), this), this.update(), i && h.DomUtil.setOpacity(this._container, 1), this.fire("open"), a.fire("popupopen", {popup: this}), this._source && this._source.fire("popupopen", {popup: this})
        }, addTo: function(a) {
            return a.addLayer(this), this
        }, openOn: function(a) {
            return a.openPopup(this), this
        }, onRemove: function(a) {
            a._panes.popupPane.removeChild(this._container), h.Util.falseFn(this._container.offsetWidth), a.off(this._getEvents(), this), a.options.fadeAnimation && h.DomUtil.setOpacity(this._container, 0), this._map = null, this.fire("close"), a.fire("popupclose", {popup: this}), this._source && this._source.fire("popupclose", {popup: this})
        }, getLatLng: function() {
            return this._latlng
        }, setLatLng: function(a) {
            return this._latlng = h.latLng(a), this._map && (this._updatePosition(), this._adjustPan()), this
        }, getContent: function() {
            return this._content
        }, setContent: function(a) {
            return this._content = a, this.update(), this
        }, update: function() {
            this._map && (this._container.style.visibility = "hidden", this._updateContent(), this._updateLayout(), this._updatePosition(), this._container.style.visibility = "", this._adjustPan())
        }, _getEvents: function() {
            var a = {viewreset: this._updatePosition};
            return this._animated && (a.zoomanim = this._zoomAnimation), ("closeOnClick" in this.options ? this.options.closeOnClick : this._map.options.closePopupOnClick) && (a.preclick = this._close), this.options.keepInView && (a.moveend = this._adjustPan), a
        }, _close: function() {
            this._map && this._map.closePopup(this)
        }, _initLayout: function() {
            var k, m = "leaflet-popup", a = m + " " + this.options.className + " leaflet-zoom-" + (this._animated ? "animated" : "hide"), o = this._container = h.DomUtil.create("div", a);
            this.options.closeButton && (k = this._closeButton = h.DomUtil.create("a", m + "-close-button", o), k.href = "#close", k.innerHTML = "&#215;", h.DomEvent.disableClickPropagation(k), h.DomEvent.on(k, "click", this._onCloseButtonClick, this));
            var l = this._wrapper = h.DomUtil.create("div", m + "-content-wrapper", o);
            h.DomEvent.disableClickPropagation(l), this._contentNode = h.DomUtil.create("div", m + "-content", l), h.DomEvent.disableScrollPropagation(this._contentNode), h.DomEvent.on(l, "contextmenu", h.DomEvent.stopPropagation), this._tipContainer = h.DomUtil.create("div", m + "-tip-container", o), this._tip = h.DomUtil.create("div", m + "-tip", this._tipContainer)
        }, _updateContent: function() {
            if (this._content) {
                if ("string" == typeof this._content) {
                    this._contentNode.innerHTML = this._content
                } else {
                    for (; this._contentNode.hasChildNodes(); ) {
                        this._contentNode.removeChild(this._contentNode.firstChild)
                    }
                    this._contentNode.appendChild(this._content)
                }
                this.fire("contentupdate")
            }
        }, _updateLayout: function() {
            var m = this._contentNode, p = m.style;
            p.width = "", p.whiteSpace = "nowrap";
            var l = m.offsetWidth;
            l = Math.min(l, this.options.maxWidth), l = Math.max(l, this.options.minWidth), p.width = l + 1 + "px", p.whiteSpace = "", p.height = "";
            var q = m.offsetHeight, o = this.options.maxHeight, k = "leaflet-popup-scrolled";
            o && q > o ? (p.height = o + "px", h.DomUtil.addClass(m, k)) : h.DomUtil.removeClass(m, k), this._containerWidth = this._container.offsetWidth
        }, _updatePosition: function() {
            if (this._map) {
                var k = this._map.latLngToLayerPoint(this._latlng), l = this._animated, a = h.point(this.options.offset);
                l && h.DomUtil.setPosition(this._container, k), this._containerBottom = -a.y - (l ? 0 : k.y), this._containerLeft = -Math.round(this._containerWidth / 2) + a.x + (l ? 0 : k.x), this._container.style.bottom = this._containerBottom + "px", this._container.style.left = this._containerLeft + "px"
            }
        }, _zoomAnimation: function(a) {
            var i = this._map._latLngToNewLayerPoint(this._latlng, a.zoom, a.center);
            h.DomUtil.setPosition(this._container, i)
        }, _adjustPan: function() {
            if (this.options.autoPan) {
                var z = this._map, v = this._container.offsetHeight, p = this._containerWidth, m = new h.Point(this._containerLeft, -v - this._containerBottom);
                this._animated && m._add(h.DomUtil.getPosition(this._container));
                var A = z.layerPointToContainerPoint(m), x = h.point(this.options.autoPanPadding), k = h.point(this.options.autoPanPaddingTopLeft || x), q = h.point(this.options.autoPanPaddingBottomRight || x), o = z.getSize(), y = 0, w = 0;
                A.x + p + q.x > o.x && (y = A.x + p - o.x + q.x), A.x - y - k.x < 0 && (y = A.x - k.x), A.y + v + q.y > o.y && (w = A.y + v - o.y + q.y), A.y - w - k.y < 0 && (w = A.y - k.y), (y || w) && z.fire("autopanstart").panBy([y, w])
            }
        }, _onCloseButtonClick: function(a) {
            this._close(), h.DomEvent.stop(a)
        }}), h.popup = function(a, i) {
        return new h.Popup(a, i)
    }, h.Map.include({openPopup: function(k, l, a) {
            if (this.closePopup(), !(k instanceof h.Popup)) {
                var m = k;
                k = new h.Popup(a).setLatLng(l).setContent(m)
            }
            return k._isOpen = !0, this._popup = k, this.addLayer(k)
        }, closePopup: function(a) {
            return a && a !== this._popup || (a = this._popup, this._popup = null), a && (this.removeLayer(a), a._isOpen = !1), this
        }}), h.Marker.include({openPopup: function() {
            return this._popup && this._map && !this._map.hasLayer(this._popup) && (this._popup.setLatLng(this._latlng), this._map.openPopup(this._popup)), this
        }, closePopup: function() {
            return this._popup && this._popup._close(), this
        }, togglePopup: function() {
            return this._popup && (this._popup._isOpen ? this.closePopup() : this.openPopup()), this
        }, bindPopup: function(k, l) {
            var a = h.point(this.options.icon.options.popupAnchor || [0, 0]);
            return a = a.add(h.Popup.prototype.options.offset), l && l.offset && (a = a.add(l.offset)), l = h.extend({offset: a}, l), this._popupHandlersAdded || (this.on("click", this.togglePopup, this).on("remove", this.closePopup, this).on("move", this._movePopup, this), this._popupHandlersAdded = !0), k instanceof h.Popup ? (h.setOptions(k, l), this._popup = k) : this._popup = new h.Popup(l, this).setContent(k), this
        }, setPopupContent: function(a) {
            return this._popup && this._popup.setContent(a), this
        }, unbindPopup: function() {
            return this._popup && (this._popup = null, this.off("click", this.togglePopup, this).off("remove", this.closePopup, this).off("move", this._movePopup, this), this._popupHandlersAdded = !1), this
        }, getPopup: function() {
            return this._popup
        }, _movePopup: function(a) {
            this._popup.setLatLng(a.latlng)
        }}), h.LayerGroup = h.Class.extend({initialize: function(k) {
            this._layers = {};
            var l, a;
            if (k) {
                for (l = 0, a = k.length; a > l; l++) {
                    this.addLayer(k[l])
                }
            }
        }, addLayer: function(a) {
            var i = this.getLayerId(a);
            return this._layers[i] = a, this._map && this._map.addLayer(a), this
        }, removeLayer: function(a) {
            var i = a in this._layers ? a : this.getLayerId(a);
            return this._map && this._layers[i] && this._map.removeLayer(this._layers[i]), delete this._layers[i], this
        }, hasLayer: function(a) {
            return a ? a in this._layers || this.getLayerId(a) in this._layers : !1
        }, clearLayers: function() {
            return this.eachLayer(this.removeLayer, this), this
        }, invoke: function(k) {
            var l, a, m = Array.prototype.slice.call(arguments, 1);
            for (l in this._layers) {
                a = this._layers[l], a[k] && a[k].apply(a, m)
            }
            return this
        }, onAdd: function(a) {
            this._map = a, this.eachLayer(a.addLayer, a)
        }, onRemove: function(a) {
            this.eachLayer(a.removeLayer, a), this._map = null
        }, addTo: function(a) {
            return a.addLayer(this), this
        }, eachLayer: function(k, l) {
            for (var a in this._layers) {
                k.call(l, this._layers[a])
            }
            return this
        }, getLayer: function(a) {
            return this._layers[a]
        }, getLayers: function() {
            var a = [];
            for (var i in this._layers) {
                a.push(this._layers[i])
            }
            return a
        }, setZIndex: function(a) {
            return this.invoke("setZIndex", a)
        }, getLayerId: function(a) {
            return h.stamp(a)
        }}), h.layerGroup = function(a) {
        return new h.LayerGroup(a)
    }, h.FeatureGroup = h.LayerGroup.extend({includes: h.Mixin.Events, statics: {EVENTS: "click dblclick mouseover mouseout mousemove contextmenu popupopen popupclose"}, addLayer: function(a) {
            return this.hasLayer(a) ? this : ("on" in a && a.on(h.FeatureGroup.EVENTS, this._propagateEvent, this), h.LayerGroup.prototype.addLayer.call(this, a), this._popupContent && a.bindPopup && a.bindPopup(this._popupContent, this._popupOptions), this.fire("layeradd", {layer: a}))
        }, removeLayer: function(a) {
            return this.hasLayer(a) ? (a in this._layers && (a = this._layers[a]), a.off(h.FeatureGroup.EVENTS, this._propagateEvent, this), h.LayerGroup.prototype.removeLayer.call(this, a), this._popupContent && this.invoke("unbindPopup"), this.fire("layerremove", {layer: a})) : this
        }, bindPopup: function(a, i) {
            return this._popupContent = a, this._popupOptions = i, this.invoke("bindPopup", a, i)
        }, openPopup: function(a) {
            for (var i in this._layers) {
                this._layers[i].openPopup(a);
                break
            }
            return this
        }, setStyle: function(a) {
            return this.invoke("setStyle", a)
        }, bringToFront: function() {
            return this.invoke("bringToFront")
        }, bringToBack: function() {
            return this.invoke("bringToBack")
        }, getBounds: function() {
            var a = new h.LatLngBounds;
            return this.eachLayer(function(i) {
                a.extend(i instanceof h.Marker ? i.getLatLng() : i.getBounds())
            }), a
        }, _propagateEvent: function(a) {
            a = h.extend({layer: a.target, target: this}, a), this.fire(a.type, a)
        }}), h.featureGroup = function(a) {
        return new h.FeatureGroup(a)
    }, h.sinh = function(a) {
        return(Math.exp(a) - Math.exp(-a)) / 2
    }, h.ttl = function(a, o, l) {
        var m = Math.pow(2, l), i = (a) / m * 360 - 180, k = Math.atan(h.sinh(Math.PI * (1 - 2 * (o) / m))), e = (1 / (Math.PI / 180)) * k;
        return{y: e, x: i}
    }, h.Path = h.Class.extend({includes: [h.Mixin.Events], statics: {CLIP_PADDING: function() {
                var k = h.Browser.mobile ? 1280 : 2000, a = (k / Math.max(d.outerWidth, d.outerHeight) - 1) / 2;
                return Math.max(0, Math.min(0.5, a))
            }()}, options: {stroke: !0, color: "#0033ff", dashArray: null, lineCap: null, lineJoin: null, weight: 5, opacity: 0.5, fill: !1, fillColor: null, fillOpacity: 0.2, clickable: !0}, initialize: function(a) {
            h.setOptions(this, a)
        }, onAdd: function(a) {
            this._map = a, this._container || (this._initElements(), this._initEvents()), this.projectLatlngs(), this._updatePath(), this._container && this._map._pathRoot.appendChild(this._container), this.fire("add"), a.on({viewreset: this.projectLatlngs, moveend: this._updatePath}, this)
        }, addTo: function(a) {
            return a.addLayer(this), this
        }, onRemove: function(a) {
            a._pathRoot.removeChild(this._container), this.fire("remove"), this._map = null, h.Browser.vml && (this._container = null, this._stroke = null, this._fill = null), a.off({viewreset: this.projectLatlngs, moveend: this._updatePath}, this)
        }, projectLatlngs: function() {
        }, setStyle: function(a) {
            return h.setOptions(this, a), this._container && this._updateStyle(), this
        }, redraw: function() {
            return this._map && (this.projectLatlngs(), this._updatePath()), this
        }}), h.Map.include({_updatePathViewport: function() {
            var k = h.Path.CLIP_PADDING, m = this.getSize(), a = h.DomUtil.getPosition(this._mapPane), o = a.multiplyBy(-1)._subtract(m.multiplyBy(k)._round()), l = o.add(m.multiplyBy(1 + 2 * k)._round());
            this._pathViewport = new h.Bounds(o, l)
        }}), h.Path.SVG_NS = "http://www.w3.org/2000/svg", h.Browser.svg = !(!g.createElementNS || !g.createElementNS(h.Path.SVG_NS, "svg").createSVGRect), h.Path = h.Path.extend({statics: {SVG: h.Browser.svg}, bringToFront: function() {
            var a = this._map._pathRoot, i = this._container;
            return i && a.lastChild !== i && a.appendChild(i), this
        }, bringToBack: function() {
            var k = this._map._pathRoot, l = this._container, a = k.firstChild;
            return l && a !== l && k.insertBefore(l, a), this
        }, getPathString: function() {
        }, _createElement: function(a) {
            return g.createElementNS(h.Path.SVG_NS, a)
        }, _initElements: function() {
            this._map._initPathRoot(), this._initPath(), this._initStyle()
        }, _initPath: function() {
            this._container = this._createElement("g"), this._path = this._createElement("path"), this.options.className && h.DomUtil.addClass(this._path, this.options.className), this._container.appendChild(this._path)
        }, _initStyle: function() {
            this.options.stroke && (this._path.setAttribute("stroke-linejoin", "round"), this._path.setAttribute("stroke-linecap", "round")), this.options.fill && this._path.setAttribute("fill-rule", "evenodd"), this.options.pointerEvents && this._path.setAttribute("pointer-events", this.options.pointerEvents), this.options.clickable || this.options.pointerEvents || this._path.setAttribute("pointer-events", "none"), this._updateStyle()
        }, _updateStyle: function() {
            this.options.stroke ? (this._path.setAttribute("stroke", this.options.color), this._path.setAttribute("stroke-opacity", this.options.opacity), this._path.setAttribute("stroke-width", this.options.weight), this.options.dashArray ? this._path.setAttribute("stroke-dasharray", this.options.dashArray) : this._path.removeAttribute("stroke-dasharray"), this.options.lineCap && this._path.setAttribute("stroke-linecap", this.options.lineCap), this.options.lineJoin && this._path.setAttribute("stroke-linejoin", this.options.lineJoin)) : this._path.setAttribute("stroke", "none"), this.options.fill ? (this._path.setAttribute("fill", this.options.fillColor || this.options.color), this._path.setAttribute("fill-opacity", this.options.fillOpacity)) : this._path.setAttribute("fill", "none")
        }, _updatePath: function() {
            var a = this.getPathString();
            a || (a = "M0 0"), this._path.setAttribute("d", a)
        }, _initEvents: function() {
            if (this.options.clickable) {
                (h.Browser.svg || !h.Browser.vml) && h.DomUtil.addClass(this._path, "leaflet-clickable"), h.DomEvent.on(this._container, "click", this._onMouseClick, this);
                for (var a = ["dblclick", "mousedown", "mouseover", "mouseout", "mousemove", "contextmenu"], i = 0; i < a.length; i++) {
                    h.DomEvent.on(this._container, a[i], this._fireMouseEvent, this)
                }
            }
        }, _onMouseClick: function(a) {
            this._map.dragging && this._map.dragging.moved() || this._fireMouseEvent(a)
        }, _fireMouseEvent: function(k) {
            if (this.hasEventListeners(k.type)) {
                var m = this._map, a = m.mouseEventToContainerPoint(k), o = m.containerPointToLayerPoint(a), l = m.layerPointToLatLng(o);
                this.fire(k.type, {latlng: l, layerPoint: o, containerPoint: a, originalEvent: k}), "contextmenu" === k.type && h.DomEvent.preventDefault(k), "mousemove" !== k.type && h.DomEvent.stopPropagation(k)
            }
        }}), h.Map.include({_initPathRoot: function() {
            this._pathRoot || (this._pathRoot = h.Path.prototype._createElement("svg"), this._panes.overlayPane.appendChild(this._pathRoot), this.options.zoomAnimation && h.Browser.any3d ? (h.DomUtil.addClass(this._pathRoot, "leaflet-zoom-animated"), this.on({zoomanim: this._animatePathZoom, zoomend: this._endPathZoom})) : h.DomUtil.addClass(this._pathRoot, "leaflet-zoom-hide"), this.on("moveend", this._updateSvgViewport), this._updateSvgViewport())
        }, _animatePathZoom: function(k) {
            var l = this.getZoomScale(k.zoom), a = this._getCenterOffset(k.center)._multiplyBy(-l)._add(this._pathViewport.min);
            this._pathRoot.style[h.DomUtil.TRANSFORM] = h.DomUtil.getTranslateString(a) + " scale(" + l + ") ", this._pathZooming = !0
        }, _endPathZoom: function() {
            this._pathZooming = !1
        }, _updateSvgViewport: function() {
            if (!this._pathZooming) {
                this._updatePathViewport();
                var m = this._pathViewport, q = m.min, l = m.max, u = l.x - q.x, o = l.y - q.y, k = this._pathRoot, p = this._panes.overlayPane;
                h.Browser.mobileWebkit && p.removeChild(k), h.DomUtil.setPosition(k, q), k.setAttribute("width", u), k.setAttribute("height", o), k.setAttribute("viewBox", [q.x, q.y, u, o].join(" ")), h.Browser.mobileWebkit && p.appendChild(k)
            }
        }}), h.Path.include({bindPopup: function(a, i) {
            return a instanceof h.Popup ? this._popup = a : ((!this._popup || i) && (this._popup = new h.Popup(i, this)), this._popup.setContent(a)), this._popupHandlersAdded || (this.on("click", this._openPopup, this).on("remove", this.closePopup, this), this._popupHandlersAdded = !0), this
        }, unbindPopup: function() {
            return this._popup && (this._popup = null, this.off("click", this._openPopup).off("remove", this.closePopup), this._popupHandlersAdded = !1), this
        }, openPopup: function(a) {
            return this._popup && (a = a || this._latlng || this._latlngs[Math.floor(this._latlngs.length / 2)], this._openPopup({latlng: a})), this
        }, closePopup: function() {
            return this._popup && this._popup._close(), this
        }, _openPopup: function(a) {
            this._popup.setLatLng(a.latlng), this._map.openPopup(this._popup)
        }}), h.Browser.vml = !h.Browser.svg && function() {
        try {
            var e = g.createElement("div");
            e.innerHTML = '<v:shape adj="1"/>';
            var a = e.firstChild;
            return a.style.behavior = "url(#default#VML)", a && "object" == typeof a.adj
        } catch (k) {
            return !1
        }
    }(), h.Path = h.Browser.svg || !h.Browser.vml ? h.Path : h.Path.extend({statics: {VML: !0, CLIP_PADDING: 0.02}, _createElement: function() {
            try {
                return g.namespaces.add("lvml", "urn:schemas-microsoft-com:vml"), function(e) {
                    return g.createElement("<lvml:" + e + ' class="lvml">')
                }
            } catch (a) {
                return function(e) {
                    return g.createElement("<" + e + ' xmlns="urn:schemas-microsoft.com:vml" class="lvml">')
                }
            }
        }(), _initPath: function() {
            var a = this._container = this._createElement("shape");
            h.DomUtil.addClass(a, "leaflet-vml-shape" + (this.options.className ? " " + this.options.className : "")), this.options.clickable && h.DomUtil.addClass(a, "leaflet-clickable"), a.coordsize = "1 1", this._path = this._createElement("path"), a.appendChild(this._path), this._map._pathRoot.appendChild(a)
        }, _initStyle: function() {
            this._updateStyle()
        }, _updateStyle: function() {
            var k = this._stroke, l = this._fill, a = this.options, m = this._container;
            m.stroked = a.stroke, m.filled = a.fill, a.stroke ? (k || (k = this._stroke = this._createElement("stroke"), k.endcap = "round", m.appendChild(k)), k.weight = a.weight + "px", k.color = a.color, k.opacity = a.opacity, k.dashStyle = a.dashArray ? h.Util.isArray(a.dashArray) ? a.dashArray.join(" ") : a.dashArray.replace(/( *, *)/g, " ") : "", a.lineCap && (k.endcap = a.lineCap.replace("butt", "flat")), a.lineJoin && (k.joinstyle = a.lineJoin)) : k && (m.removeChild(k), this._stroke = null), a.fill ? (l || (l = this._fill = this._createElement("fill"), m.appendChild(l)), l.color = a.fillColor || a.color, l.opacity = a.fillOpacity) : l && (m.removeChild(l), this._fill = null)
        }, _updatePath: function() {
            var a = this._container.style;
            a.display = "none", this._path.v = this.getPathString() + " ", a.display = ""
        }}), h.Map.include(h.Browser.svg || !h.Browser.vml ? {} : {_initPathRoot: function() {
            if (!this._pathRoot) {
                var a = this._pathRoot = g.createElement("div");
                a.className = "leaflet-vml-container", this._panes.overlayPane.appendChild(a), this.on("moveend", this._updatePathViewport), this._updatePathViewport()
            }
        }}), h.Browser.canvas = function() {
        return !!g.createElement("canvas").getContext
    }(), h.Path = h.Path.SVG && !d.L_PREFER_CANVAS || !h.Browser.canvas ? h.Path : h.Path.extend({statics: {CANVAS: !0, SVG: !1}, redraw: function() {
            return this._map && (this.projectLatlngs(), this._requestUpdate()), this
        }, setStyle: function(a) {
            return h.setOptions(this, a), this._map && (this._updateStyle(), this._requestUpdate()), this
        }, onRemove: function(a) {
            a.off("viewreset", this.projectLatlngs, this).off("moveend", this._updatePath, this), this.options.clickable && (this._map.off("click", this._onClick, this), this._map.off("mousemove", this._onMouseMove, this)), this._requestUpdate(), this.fire("remove"), this._map = null
        }, _requestUpdate: function() {
            this._map && !h.Path._updateRequest && (h.Path._updateRequest = h.Util.requestAnimFrame(this._fireMapMoveEnd, this._map))
        }, _fireMapMoveEnd: function() {
            h.Path._updateRequest = null, this.fire("moveend")
        }, _initElements: function() {
            this._map._initPathRoot(), this._ctx = this._map._canvasCtx
        }, _updateStyle: function() {
            var a = this.options;
            a.stroke && (this._ctx.lineWidth = a.weight, this._ctx.strokeStyle = a.color), a.fill && (this._ctx.fillStyle = a.fillColor || a.color)
        }, _drawPath: function() {
            var m, p, l, q, o, k;
            for (this._ctx.beginPath(), m = 0, l = this._parts.length; l > m; m++) {
                for (p = 0, q = this._parts[m].length; q > p; p++) {
                    o = this._parts[m][p], k = (0 === p ? "move" : "line") + "To", this._ctx[k](o.x, o.y)
                }
                this instanceof h.Polygon && this._ctx.closePath()
            }
        }, _checkIfEmpty: function() {
            return !this._parts.length
        }, _updatePath: function() {
            if (!this._checkIfEmpty()) {
                var a = this._ctx, i = this.options;
                this._drawPath(), a.save(), this._updateStyle(), i.fill && (a.globalAlpha = i.fillOpacity, a.fill()), i.stroke && (a.globalAlpha = i.opacity, a.stroke()), a.restore()
            }
        }, _initEvents: function() {
            this.options.clickable && (this._map.on("mousemove", this._onMouseMove, this), this._map.on("click", this._onClick, this))
        }, _onClick: function(a) {
            this._containsPoint(a.layerPoint) && this.fire("click", a)
        }, _onMouseMove: function(a) {
            this._map && !this._map._animatingZoom && (this._containsPoint(a.layerPoint) ? (this._ctx.canvas.style.cursor = "pointer", this._mouseInside = !0, this.fire("mouseover", a)) : this._mouseInside && (this._ctx.canvas.style.cursor = "", this._mouseInside = !1, this.fire("mouseout", a)))
        }}), h.Map.include(h.Path.SVG && !d.L_PREFER_CANVAS || !h.Browser.canvas ? {} : {_initPathRoot: function() {
            var e, a = this._pathRoot;
            a || (a = this._pathRoot = g.createElement("canvas"), a.style.position = "absolute", e = this._canvasCtx = a.getContext("2d"), e.lineCap = "round", e.lineJoin = "round", this._panes.overlayPane.appendChild(a), this.options.zoomAnimation && (this._pathRoot.className = "leaflet-zoom-animated", this.on("zoomanim", this._animatePathZoom), this.on("zoomend", this._endPathZoom)), this.on("moveend", this._updateCanvasViewport), this._updateCanvasViewport())
        }, _updateCanvasViewport: function() {
            if (!this._pathZooming) {
                this._updatePathViewport();
                var k = this._pathViewport, l = k.min, a = k.max.subtract(l), m = this._pathRoot;
                h.DomUtil.setPosition(m, l), m.width = a.x, m.height = a.y, m.getContext("2d").translate(-l.x, -l.y)
            }
        }}), h.LineUtil = {simplify: function(k, l) {
            if (!l || !k.length) {
                return k.slice()
            }
            var a = l * l;
            return k = this._reducePoints(k, a), k = this._simplifyDP(k, a)
        }, pointToSegmentDistance: function(k, l, a) {
            return Math.sqrt(this._sqClosestPointOnSegment(k, l, a, !0))
        }, closestPointOnSegment: function(k, l, a) {
            return this._sqClosestPointOnSegment(k, l, a)
        }, _simplifyDP: function(k, p) {
            var u = k.length, q = typeof Uint8Array != c + "" ? Uint8Array : Array, l = new q(u);
            l[0] = l[u - 1] = 1, this._simplifyDPStep(k, l, p, 0, u - 1);
            var i, m = [];
            for (i = 0; u > i; i++) {
                l[i] && m.push(k[i])
            }
            return m
        }, _simplifyDPStep: function(w, u, p, m, l) {
            var x, v, k, q = 0;
            for (v = m + 1; l - 1 >= v; v++) {
                k = this._sqClosestPointOnSegment(w[v], w[m], w[l], !0), k > q && (x = v, q = k)
            }
            q > p && (u[x] = 1, this._simplifyDPStep(w, u, p, m, x), this._simplifyDPStep(w, u, p, x, l))
        }, _reducePoints: function(k, m) {
            for (var a = [k[0]], q = 1, p = 0, l = k.length; l > q; q++) {
                this._sqDist(k[q], k[p]) > m && (a.push(k[q]), p = q)
            }
            return l - 1 > p && a.push(k[l - 1]), a
        }, clipSegment: function(w, u, p, m) {
            var l, x, v, k = m ? this._lastCode : this._getBitCode(w, p), q = this._getBitCode(u, p);
            for (this._lastCode = q; ; ) {
                if (!(k | q)) {
                    return[w, u]
                }
                if (k & q) {
                    return !1
                }
                l = k || q, x = this._getEdgeIntersection(w, u, l, p), v = this._getBitCode(x, p), l === k ? (w = x, k = v) : (u = x, q = v)
            }
        }, _getEdgeIntersection: function(m, u, l, v) {
            var p = u.x - m.x, k = u.y - m.y, q = v.min, o = v.max;
            return 8 & l ? new h.Point(m.x + p * (o.y - m.y) / k, o.y) : 4 & l ? new h.Point(m.x + p * (q.y - m.y) / k, q.y) : 2 & l ? new h.Point(o.x, m.y + k * (o.x - m.x) / p) : 1 & l ? new h.Point(q.x, m.y + k * (q.x - m.x) / p) : void 0
        }, _getBitCode: function(k, l) {
            var a = 0;
            return k.x < l.min.x ? a |= 1 : k.x > l.max.x && (a |= 2), k.y < l.min.y ? a |= 4 : k.y > l.max.y && (a |= 8), a
        }, _sqDist: function(k, l) {
            var a = l.x - k.x, m = l.y - k.y;
            return a * a + m * m
        }, _sqClosestPointOnSegment: function(y, v, p, m) {
            var z, w = v.x, k = v.y, q = p.x - w, o = p.y - k, x = q * q + o * o;
            return x > 0 && (z = ((y.x - w) * q + (y.y - k) * o) / x, z > 1 ? (w = p.x, k = p.y) : z > 0 && (w += q * z, k += o * z)), q = y.x - w, o = y.y - k, m ? q * q + o * o : new h.Point(w, k)
        }}, h.Polyline = h.Path.extend({initialize: function(a, i) {
            h.Path.prototype.initialize.call(this, i), this._latlngs = this._convertLatLngs(a)
        }, options: {smoothFactor: 1, noClip: !1}, projectLatlngs: function() {
            this._originalPoints = [];
            for (var a = 0, i = this._latlngs.length; i > a; a++) {
                this._originalPoints[a] = this._map.latLngToLayerPoint(this._latlngs[a])
            }
        }, getPathString: function() {
            for (var k = 0, l = this._parts.length, a = ""; l > k; k++) {
                a += this._getPathPartStr(this._parts[k])
            }
            return a
        }, getLatLngs: function() {
            return this._latlngs
        }, setLatLngs: function(a) {
            return this._latlngs = this._convertLatLngs(a), this.redraw()
        }, addLatLng: function(a) {
            return this._latlngs.push(h.latLng(a)), this.redraw()
        }, spliceLatLngs: function() {
            var a = [].splice.apply(this._latlngs, arguments);
            return this._convertLatLngs(this._latlngs, !0), this.redraw(), a
        }, closestLayerPoint: function(A) {
            for (var v, p, m = 1 / 0, B = this._parts, y = null, k = 0, q = B.length; q > k; k++) {
                for (var o = B[k], z = 1, x = o.length; x > z; z++) {
                    v = o[z - 1], p = o[z];
                    var w = h.LineUtil._sqClosestPointOnSegment(A, v, p, !0);
                    m > w && (m = w, y = h.LineUtil._sqClosestPointOnSegment(A, v, p))
                }
            }
            return y && (y.distance = Math.sqrt(m)), y
        }, getBounds: function() {
            return new h.LatLngBounds(this.getLatLngs())
        }, _convertLatLngs: function(k, m) {
            var a, o, l = m ? k : [];
            for (a = 0, o = k.length; o > a; a++) {
                if (h.Util.isArray(k[a]) && "number" != typeof k[a][0]) {
                    return
                }
                l[a] = h.latLng(k[a])
            }
            return l
        }, _initEvents: function() {
            h.Path.prototype._initEvents.call(this)
        }, _getPathPartStr: function(m) {
            for (var p, l = h.Path.VML, q = 0, o = m.length, k = ""; o > q; q++) {
                p = m[q], l && p._round(), k += (q ? "L" : "M") + p.x + " " + p.y
            }
            return k
        }, _clipPoints: function() {
            var m, u, l, v = this._originalPoints, p = v.length;
            if (this.options.noClip) {
                return void (this._parts = [v])
            }
            this._parts = [];
            var k = this._parts, q = this._map._pathViewport, o = h.LineUtil;
            for (m = 0, u = 0; p - 1 > m; m++) {
                l = o.clipSegment(v[m], v[m + 1], q, m), l && (k[u] = k[u] || [], k[u].push(l[0]), (l[1] !== v[m + 1] || m === p - 2) && (k[u].push(l[1]), u++))
            }
        }, _simplifyPoints: function() {
            for (var k = this._parts, l = h.LineUtil, a = 0, m = k.length; m > a; a++) {
                k[a] = l.simplify(k[a], this.options.smoothFactor)
            }
        }, _updatePath: function() {
            this._map && (this._clipPoints(), this._simplifyPoints(), h.Path.prototype._updatePath.call(this))
        }}), h.polyline = function(a, i) {
        return new h.Polyline(a, i)
    }, h.PolyUtil = {}, h.PolyUtil.clipPolygon = function(C, x) {
        var v, o, D, A, k, w, q, B, z, y = [1, 4, 2, 8], m = h.LineUtil;
        for (o = 0, q = C.length; q > o; o++) {
            C[o]._code = m._getBitCode(C[o], x)
        }
        for (A = 0; 4 > A; A++) {
            for (B = y[A], v = [], o = 0, q = C.length, D = q - 1; q > o; D = o++) {
                k = C[o], w = C[D], k._code & B ? w._code & B || (z = m._getEdgeIntersection(w, k, B, x), z._code = m._getBitCode(z, x), v.push(z)) : (w._code & B && (z = m._getEdgeIntersection(w, k, B, x), z._code = m._getBitCode(z, x), v.push(z)), v.push(k))
            }
            C = v
        }
        return C
    }, h.Polygon = h.Polyline.extend({options: {fill: !0}, initialize: function(a, i) {
            h.Polyline.prototype.initialize.call(this, a, i), this._initWithHoles(a)
        }, _initWithHoles: function(k) {
            var l, a, m;
            if (k && h.Util.isArray(k[0]) && "number" != typeof k[0][0]) {
                for (this._latlngs = this._convertLatLngs(k[0]), this._holes = k.slice(1), l = 0, a = this._holes.length; a > l; l++) {
                    m = this._holes[l] = this._convertLatLngs(this._holes[l]), m[0].equals(m[m.length - 1]) && m.pop()
                }
            }
            k = this._latlngs, k.length >= 2 && k[0].equals(k[k.length - 1]) && k.pop()
        }, projectLatlngs: function() {
            if (h.Polyline.prototype.projectLatlngs.call(this), this._holePoints = [], this._holes) {
                var k, l, a, m;
                for (k = 0, a = this._holes.length; a > k; k++) {
                    for (this._holePoints[k] = [], l = 0, m = this._holes[k].length; m > l; l++) {
                        this._holePoints[k][l] = this._map.latLngToLayerPoint(this._holes[k][l])
                    }
                }
            }
        }, setLatLngs: function(a) {
            return a && h.Util.isArray(a[0]) && "number" != typeof a[0][0] ? (this._initWithHoles(a), this.redraw()) : h.Polyline.prototype.setLatLngs.call(this, a)
        }, _clipPoints: function() {
            var k = this._originalPoints, m = [];
            if (this._parts = [k].concat(this._holePoints), !this.options.noClip) {
                for (var a = 0, o = this._parts.length; o > a; a++) {
                    var l = h.PolyUtil.clipPolygon(this._parts[a], this._map._pathViewport);
                    l.length && m.push(l)
                }
                this._parts = m
            }
        }, _getPathPartStr: function(a) {
            var i = h.Polyline.prototype._getPathPartStr.call(this, a);
            return i + (h.Browser.svg ? "z" : "x")
        }}), h.polygon = function(a, i) {
        return new h.Polygon(a, i)
    }, function() {
        function a(e) {
            return h.FeatureGroup.extend({initialize: function(i, k) {
                    this._layers = {}, this._options = k, this.setLatLngs(i)
                }, setLatLngs: function(l) {
                    var k = 0, m = l.length;
                    for (this.eachLayer(function(i) {
                        m > k ? i.setLatLngs(l[k++]) : this.removeLayer(i)
                    }, this); m > k; ) {
                        this.addLayer(new e(l[k++], this._options))
                    }
                    return this
                }, getLatLngs: function() {
                    var i = [];
                    return this.eachLayer(function(k) {
                        i.push(k.getLatLngs())
                    }), i
                }})
        }
        h.MultiPolyline = a(h.Polyline), h.MultiPolygon = a(h.Polygon), h.multiPolyline = function(i, k) {
            return new h.MultiPolyline(i, k)
        }, h.multiPolygon = function(i, k) {
            return new h.MultiPolygon(i, k)
        }
    }(), h.Rectangle = h.Polygon.extend({initialize: function(a, i) {
            h.Polygon.prototype.initialize.call(this, this._boundsToLatLngs(a), i)
        }, setBounds: function(a) {
            this.setLatLngs(this._boundsToLatLngs(a))
        }, _boundsToLatLngs: function(a) {
            return a = h.latLngBounds(a), [a.getSouthWest(), a.getNorthWest(), a.getNorthEast(), a.getSouthEast()]
        }}), h.rectangle = function(a, i) {
        return new h.Rectangle(a, i)
    }, h.Circle = h.Path.extend({initialize: function(k, l, a) {
            h.Path.prototype.initialize.call(this, a), this._latlng = h.latLng(k), this._mRadius = l
        }, options: {fill: !0}, setLatLng: function(a) {
            return this._latlng = h.latLng(a), this.redraw()
        }, setRadius: function(a) {
            return this._mRadius = a, this.redraw()
        }, projectLatlngs: function() {
            var k = this._getLngRadius(), l = this._latlng, a = this._map.latLngToLayerPoint([l.lat, l.lng - k]);
            this._point = this._map.latLngToLayerPoint(l), this._radius = Math.max(this._point.x - a.x, 1)
        }, getBounds: function() {
            var k = this._getLngRadius(), l = this._mRadius / 40075017 * 360, a = this._latlng;
            return new h.LatLngBounds([a.lat - l, a.lng - k], [a.lat + l, a.lng + k])
        }, getLatLng: function() {
            return this._latlng
        }, getPathString: function() {
            var a = this._point, i = this._radius;
            return this._checkIfEmpty() ? "" : h.Browser.svg ? "M" + a.x + "," + (a.y - i) + "A" + i + "," + i + ",0,1,1," + (a.x - 0.1) + "," + (a.y - i) + " z" : (a._round(), i = Math.round(i), "AL " + a.x + "," + a.y + " " + i + "," + i + " 0,23592600")
        }, getRadius: function() {
            return this._mRadius
        }, _getLatRadius: function() {
            return this._mRadius / 40075017 * 360
        }, _getLngRadius: function() {
            return this._getLatRadius() / Math.cos(h.LatLng.DEG_TO_RAD * this._latlng.lat)
        }, _checkIfEmpty: function() {
            if (!this._map) {
                return !1
            }
            var k = this._map._pathViewport, l = this._radius, a = this._point;
            return a.x - l > k.max.x || a.y - l > k.max.y || a.x + l < k.min.x || a.y + l < k.min.y
        }}), h.circle = function(k, l, a) {
        return new h.Circle(k, l, a)
    }, h.CircleMarker = h.Circle.extend({options: {radius: 10, weight: 2}, initialize: function(a, i) {
            h.Circle.prototype.initialize.call(this, a, null, i), this._radius = this.options.radius
        }, projectLatlngs: function() {
            this._point = this._map.latLngToLayerPoint(this._latlng)
        }, _updateStyle: function() {
            h.Circle.prototype._updateStyle.call(this), this.setRadius(this.options.radius)
        }, setLatLng: function(a) {
            return h.Circle.prototype.setLatLng.call(this, a), this._popup && this._popup._isOpen && this._popup.setLatLng(a), this
        }, setRadius: function(a) {
            return this.options.radius = this._radius = a, this.redraw()
        }, getRadius: function() {
            return this._radius
        }}), h.circleMarker = function(a, i) {
        return new h.CircleMarker(a, i)
    }, h.Polyline.include(h.Path.CANVAS ? {_containsPoint: function(y, v) {
            var p, m, z, w, k, q, o, x = this.options.weight / 2;
            for (h.Browser.touch && (x += 10), p = 0, w = this._parts.length; w > p; p++) {
                for (o = this._parts[p], m = 0, k = o.length, z = k - 1; k > m; z = m++) {
                    if ((v || 0 !== m) && (q = h.LineUtil.pointToSegmentDistance(y, o[z], o[m]), x >= q)) {
                        return !0
                    }
                }
            }
            return !1
        }} : {}), h.Polygon.include(h.Path.CANVAS ? {_containsPoint: function(y) {
            var v, p, m, z, w, k, q, o, x = !1;
            if (h.Polyline.prototype._containsPoint.call(this, y, !0)) {
                return !0
            }
            for (z = 0, q = this._parts.length; q > z; z++) {
                for (v = this._parts[z], w = 0, o = v.length, k = o - 1; o > w; k = w++) {
                    p = v[w], m = v[k], p.y > y.y != m.y > y.y && y.x < (m.x - p.x) * (y.y - p.y) / (m.y - p.y) + p.x && (x = !x)
                }
            }
            return x
        }} : {}), h.Circle.include(h.Path.CANVAS ? {_drawPath: function() {
            var a = this._point;
            this._ctx.beginPath(), this._ctx.arc(a.x, a.y, this._radius, 0, 2 * Math.PI, !1)
        }, _containsPoint: function(k) {
            var l = this._point, a = this.options.stroke ? this.options.weight / 2 : 0;
            return k.distanceTo(l) <= this._radius + a
        }} : {}), h.CircleMarker.include(h.Path.CANVAS ? {_updateStyle: function() {
            h.Path.prototype._updateStyle.call(this)
        }} : {}), h.GeoJSON = h.FeatureGroup.extend({initialize: function(a, i) {
            h.setOptions(this, i), this._layers = {}, a && this.addData(a)
        }, addData: function(m) {
            var q, l, u, o = h.Util.isArray(m) ? m : m.features;
            if (o) {
                for (q = 0, l = o.length; l > q; q++) {
                    u = o[q], (u.geometries || u.geometry || u.features || u.coordinates) && this.addData(o[q])
                }
                return this
            }
            var k = this.options;
            if (!k.filter || k.filter(m)) {
                var p = h.GeoJSON.geometryToLayer(m, k.pointToLayer, k.coordsToLatLng, k);
                return p.feature = h.GeoJSON.asFeature(m), p.defaultOptions = p.options, this.resetStyle(p), k.onEachFeature && k.onEachFeature(m, p), this.addLayer(p)
            }
        }, resetStyle: function(a) {
            var i = this.options.style;
            i && (h.Util.extend(a.options, a.defaultOptions), this._setLayerStyle(a, i))
        }, setStyle: function(a) {
            this.eachLayer(function(i) {
                this._setLayerStyle(i, a)
            }, this)
        }, _setLayerStyle: function(a, i) {
            "function" == typeof i && (i = i(a.feature)), a.setStyle && a.setStyle(i)
        }}), h.extend(h.GeoJSON, {geometryToLayer: function(z, v, p, m) {
            var A, x, k, q, o = "Feature" === z.type ? z.geometry : z, y = o.coordinates, w = [];
            switch (p = p || this.coordsToLatLng, o.type) {
                case"Point":
                    return A = p(y), v ? v(z, A) : new h.Marker(A);
                case"MultiPoint":
                    for (k = 0, q = y.length; q > k; k++) {
                        A = p(y[k]), w.push(v ? v(z, A) : new h.Marker(A))
                    }
                    return new h.FeatureGroup(w);
                case"LineString":
                    return x = this.coordsToLatLngs(y, 0, p), new h.Polyline(x, m);
                case"Polygon":
                    if (2 === y.length && !y[1].length) {
                        throw new Error("Invalid GeoJSON object.")
                    }
                    return x = this.coordsToLatLngs(y, 1, p), new h.Polygon(x, m);
                case"MultiLineString":
                    return x = this.coordsToLatLngs(y, 1, p), new h.MultiPolyline(x, m);
                case"MultiPolygon":
                    return x = this.coordsToLatLngs(y, 2, p), new h.MultiPolygon(x, m);
                case"GeometryCollection":
                    for (k = 0, q = o.geometries.length; q > k; k++) {
                        w.push(this.geometryToLayer({geometry: o.geometries[k], type: "Feature", properties: z.properties}, v, p, m))
                    }
                    return new h.FeatureGroup(w);
                default:
                    throw new Error("Invalid GeoJSON object.")
                }
        }, coordsToLatLng: function(a) {
            return new h.LatLng(a[1], a[0], a[2])
        }, coordsToLatLngs: function(m, q, l) {
            var u, r, p, k = [];
            for (r = 0, p = m.length; p > r; r++) {
                u = q ? this.coordsToLatLngs(m[r], q - 1, l) : (l || this.coordsToLatLng)(m[r]), k.push(u)
            }
            return k
        }, latLngToCoords: function(a) {
            var i = [a.lng, a.lat];
            return a.alt !== c && i.push(a.alt), i
        }, latLngsToCoords: function(k) {
            for (var l = [], a = 0, m = k.length; m > a; a++) {
                l.push(h.GeoJSON.latLngToCoords(k[a]))
            }
            return l
        }, getFeature: function(a, i) {
            return a.feature ? h.extend({}, a.feature, {geometry: i}) : h.GeoJSON.asFeature(i)
        }, asFeature: function(a) {
            return"Feature" === a.type ? a : {type: "Feature", properties: {}, geometry: a}
        }});
    var b = {toGeoJSON: function() {
            return h.GeoJSON.getFeature(this, {type: "Point", coordinates: h.GeoJSON.latLngToCoords(this.getLatLng())})
        }};
    h.Marker.include(b), h.Circle.include(b), h.CircleMarker.include(b), h.Polyline.include({toGeoJSON: function() {
            return h.GeoJSON.getFeature(this, {type: "LineString", coordinates: h.GeoJSON.latLngsToCoords(this.getLatLngs())})
        }}), h.Polygon.include({toGeoJSON: function() {
            var k, l, a, m = [h.GeoJSON.latLngsToCoords(this.getLatLngs())];
            if (m[0].push(m[0][0]), this._holes) {
                for (k = 0, l = this._holes.length; l > k; k++) {
                    a = h.GeoJSON.latLngsToCoords(this._holes[k]), a.push(a[0]), m.push(a)
                }
            }
            return h.GeoJSON.getFeature(this, {type: "Polygon", coordinates: m})
        }}), function() {
        function a(e) {
            return function() {
                var i = [];
                return this.eachLayer(function(k) {
                    i.push(k.toGeoJSON().geometry.coordinates)
                }), h.GeoJSON.getFeature(this, {type: e, coordinates: i})
            }
        }
        h.MultiPolyline.include({toGeoJSON: a("MultiLineString")}), h.MultiPolygon.include({toGeoJSON: a("MultiPolygon")}), h.LayerGroup.include({toGeoJSON: function() {
                var m, k = this.feature && this.feature.geometry, o = [];
                if (k && "MultiPoint" === k.type) {
                    return a("MultiPoint").call(this)
                }
                var l = k && "GeometryCollection" === k.type;
                return this.eachLayer(function(e) {
                    e.toGeoJSON && (m = e.toGeoJSON(), o.push(l ? m.geometry : h.GeoJSON.asFeature(m)))
                }), l ? h.GeoJSON.getFeature(this, {geometries: o, type: "GeometryCollection"}) : {type: "FeatureCollection", features: o}
            }})
    }(), h.geoJson = function(a, i) {
        return new h.GeoJSON(a, i)
    }, h.DomEvent = {addListener: function(w, u, p, m) {
            var x, v, k, q = h.stamp(p), o = "_leaflet_" + u + q;
            return w[o] ? this : (x = function(a) {
                return p.call(m || w, a || h.DomEvent._getEvent())
            }, h.Browser.pointer && 0 === u.indexOf("touch") ? this.addPointerListener(w, u, x, q) : (h.Browser.touch && "dblclick" === u && this.addDoubleTapListener && this.addDoubleTapListener(w, x, q), "addEventListener" in w ? "mousewheel" === u ? (w.addEventListener("DOMMouseScroll", x, !1), w.addEventListener(u, x, !1)) : "mouseenter" === u || "mouseleave" === u ? (v = x, k = "mouseenter" === u ? "mouseover" : "mouseout", x = function(a) {
                return h.DomEvent._checkMouse(w, a) ? v(a) : void 0
            }, w.addEventListener(k, x, !1)) : "click" === u && h.Browser.android ? (v = x, x = function(a) {
                return h.DomEvent._filterClick(a, v)
            }, w.addEventListener(u, x, !1)) : w.addEventListener(u, x, !1) : "attachEvent" in w && w.attachEvent("on" + u, x), w[o] = x, this))
        }, removeListener: function(m, p, l) {
            var q = h.stamp(l), o = "_leaflet_" + p + q, k = m[o];
            return k ? (h.Browser.pointer && 0 === p.indexOf("touch") ? this.removePointerListener(m, p, q) : h.Browser.touch && "dblclick" === p && this.removeDoubleTapListener ? this.removeDoubleTapListener(m, q) : "removeEventListener" in m ? "mousewheel" === p ? (m.removeEventListener("DOMMouseScroll", k, !1), m.removeEventListener(p, k, !1)) : "mouseenter" === p || "mouseleave" === p ? m.removeEventListener("mouseenter" === p ? "mouseover" : "mouseout", k, !1) : m.removeEventListener(p, k, !1) : "detachEvent" in m && m.detachEvent("on" + p, k), m[o] = null, this) : this
        }, stopPropagation: function(a) {
            return a.stopPropagation ? a.stopPropagation() : a.cancelBubble = !0, h.DomEvent._skipped(a), this
        }, disableScrollPropagation: function(a) {
            var i = h.DomEvent.stopPropagation;
            return h.DomEvent.on(a, "mousewheel", i).on(a, "MozMousePixelScroll", i)
        }, disableClickPropagation: function(k) {
            for (var l = h.DomEvent.stopPropagation, a = h.Draggable.START.length - 1; a >= 0; a--) {
                h.DomEvent.on(k, h.Draggable.START[a], l)
            }
            return h.DomEvent.on(k, "click", h.DomEvent._fakeStop).on(k, "dblclick", l)
        }, preventDefault: function(a) {
            return a.preventDefault ? a.preventDefault() : a.returnValue = !1, this
        }, stop: function(a) {
            return h.DomEvent.preventDefault(a).stopPropagation(a)
        }, getMousePosition: function(k, l) {
            if (!l) {
                return new h.Point(k.clientX, k.clientY)
            }
            var a = l.getBoundingClientRect();
            return new h.Point(k.clientX - a.left - l.clientLeft, k.clientY - a.top - l.clientTop)
        }, getWheelDelta: function(a) {
            var i = 0;
            return a.wheelDelta && (i = a.wheelDelta / 120), a.detail && (i = -a.detail / 3), i
        }, _skipEvents: {}, _fakeStop: function(a) {
            h.DomEvent._skipEvents[a.type] = !0
        }, _skipped: function(a) {
            var i = this._skipEvents[a.type];
            return this._skipEvents[a.type] = !1, i
        }, _checkMouse: function(k, l) {
            var a = l.relatedTarget;
            if (!a) {
                return !0
            }
            try {
                for (; a && a !== k; ) {
                    a = a.parentNode
                }
            } catch (m) {
                return !1
            }
            return a !== k
        }, _getEvent: function() {
            var k = d.event;
            if (!k) {
                for (var a = arguments.callee.caller; a && (k = a.arguments[0], !k || d.Event !== k.constructor); ) {
                    a = a.caller
                }
            }
            return k
        }, _filterClick: function(k, l) {
            var a = k.timeStamp || k.originalEvent.timeStamp, m = h.DomEvent._lastClick && a - h.DomEvent._lastClick;
            return m && m > 100 && 500 > m || k.target._simulatedClick && !k._simulated ? void h.DomEvent.stop(k) : (h.DomEvent._lastClick = a, l(k))
        }}, h.DomEvent.on = h.DomEvent.addListener, h.DomEvent.off = h.DomEvent.removeListener, h.Draggable = h.Class.extend({includes: h.Mixin.Events, statics: {START: h.Browser.touch ? ["touchstart", "mousedown"] : ["mousedown"], END: {mousedown: "mouseup", touchstart: "touchend", pointerdown: "touchend", MSPointerDown: "touchend"}, MOVE: {mousedown: "mousemove", touchstart: "touchmove", pointerdown: "touchmove", MSPointerDown: "touchmove"}}, initialize: function(a, i) {
            this._element = a, this._dragStartTarget = i || a
        }, enable: function() {
            if (!this._enabled) {
                for (var a = h.Draggable.START.length - 1; a >= 0; a--) {
                    h.DomEvent.on(this._dragStartTarget, h.Draggable.START[a], this._onDown, this)
                }
                this._enabled = !0
            }
        }, disable: function() {
            if (this._enabled) {
                for (var a = h.Draggable.START.length - 1; a >= 0; a--) {
                    h.DomEvent.off(this._dragStartTarget, h.Draggable.START[a], this._onDown, this)
                }
                this._enabled = !1, this._moved = !1
            }
        }, _onDown: function(e) {
            if (this._moved = !1, !(e.shiftKey || 1 !== e.which && 1 !== e.button && !e.touches || (h.DomEvent.stopPropagation(e), h.Draggable._disabled || (h.DomUtil.disableImageDrag(), h.DomUtil.disableTextSelection(), this._moving)))) {
                var a = e.touches ? e.touches[0] : e;
                this._startPoint = new h.Point(a.clientX, a.clientY), this._startPos = this._newPos = h.DomUtil.getPosition(this._element), h.DomEvent.on(g, h.Draggable.MOVE[e.type], this._onMove, this).on(g, h.Draggable.END[e.type], this._onUp, this)
            }
        }, _onMove: function(e) {
            if (e.touches && e.touches.length > 1) {
                return void (this._moved = !0)
            }
            var a = e.touches && 1 === e.touches.length ? e.touches[0] : e, l = new h.Point(a.clientX, a.clientY), k = l.subtract(this._startPoint);
            (k.x || k.y) && (h.Browser.touch && Math.abs(k.x) + Math.abs(k.y) < 3 || (h.DomEvent.preventDefault(e), this._moved || (this.fire("dragstart"), this._moved = !0, this._startPos = h.DomUtil.getPosition(this._element).subtract(k), h.DomUtil.addClass(g.body, "leaflet-dragging"), this._lastTarget = e.target || e.srcElement, h.DomUtil.addClass(this._lastTarget, "leaflet-drag-target")), this._newPos = this._startPos.add(k), this._moving = !0, h.Util.cancelAnimFrame(this._animRequest), this._animRequest = h.Util.requestAnimFrame(this._updatePosition, this, !0, this._dragStartTarget)))
        }, _updatePosition: function() {
            this.fire("predrag"), h.DomUtil.setPosition(this._element, this._newPos), this.fire("drag")
        }, _onUp: function() {
            h.DomUtil.removeClass(g.body, "leaflet-dragging"), this._lastTarget && (h.DomUtil.removeClass(this._lastTarget, "leaflet-drag-target"), this._lastTarget = null);
            for (var a in h.Draggable.MOVE) {
                h.DomEvent.off(g, h.Draggable.MOVE[a], this._onMove).off(g, h.Draggable.END[a], this._onUp)
            }
            h.DomUtil.enableImageDrag(), h.DomUtil.enableTextSelection(), this._moved && this._moving && (h.Util.cancelAnimFrame(this._animRequest), this.fire("dragend", {distance: this._newPos.distanceTo(this._startPos)})), this._moving = !1
        }}), h.Handler = h.Class.extend({initialize: function(a) {
            this._map = a
        }, enable: function() {
            this._enabled || (this._enabled = !0, this.addHooks())
        }, disable: function() {
            this._enabled && (this._enabled = !1, this.removeHooks())
        }, enabled: function() {
            return !!this._enabled
        }}), h.Map.mergeOptions({dragging: !0, inertia: !h.Browser.android23, inertiaDeceleration: 3400, inertiaMaxSpeed: 1 / 0, inertiaThreshold: h.Browser.touch ? 32 : 18, easeLinearity: 0.25, worldCopyJump: !1}), h.Map.Drag = h.Handler.extend({addHooks: function() {
            if (!this._draggable) {
                var a = this._map;
                this._draggable = new h.Draggable(a._mapPane, a._container), this._draggable.on({dragstart: this._onDragStart, drag: this._onDrag, dragend: this._onDragEnd}, this), a.options.worldCopyJump && (this._draggable.on("predrag", this._onPreDrag, this), a.on("viewreset", this._onViewReset, this), a.whenReady(this._onViewReset, this))
            }
            this._draggable.enable()
        }, removeHooks: function() {
            this._draggable.disable()
        }, moved: function() {
            return this._draggable && this._draggable._moved
        }, _onDragStart: function() {
            var a = this._map;
            a._panAnim && a._panAnim.stop(), a.fire("movestart").fire("dragstart"), a.options.inertia && (this._positions = [], this._times = [])
        }, _onDrag: function() {
            if (this._map.options.inertia) {
                var a = this._lastTime = +new Date, i = this._lastPos = this._draggable._newPos;
                this._positions.push(i), this._times.push(a), a - this._times[0] > 200 && (this._positions.shift(), this._times.shift())
            }
            this._map.fire("move").fire("drag")
        }, _onViewReset: function() {
            var a = this._map.getSize()._divideBy(2), i = this._map.latLngToLayerPoint([0, 0]);
            this._initialWorldOffset = i.subtract(a).x, this._worldWidth = this._map.project([0, 180]).x
        }, _onPreDrag: function() {
            var m = this._worldWidth, q = Math.round(m / 2), l = this._initialWorldOffset, u = this._draggable._newPos.x, r = (u - q + l) % m + q - l, p = (u + q + l) % m - q - l, k = Math.abs(r + l) < Math.abs(p + l) ? r : p;
            this._draggable._newPos.x = k
        }, _onDragEnd: function(D) {
            var x = this._map, v = x.options, o = +new Date - this._lastTime, E = !v.inertia || o > v.inertiaThreshold || !this._positions[0];
            if (x.fire("dragend", D), E) {
                x.fire("moveend")
            } else {
                var A = this._lastPos.subtract(this._positions[0]), k = (this._lastTime + o - this._times[0]) / 1000, w = v.easeLinearity, q = A.multiplyBy(w / k), C = q.distanceTo([0, 0]), z = Math.min(v.inertiaMaxSpeed, C), y = q.multiplyBy(z / C), m = z / (v.inertiaDeceleration * w), B = y.multiplyBy(-m / 2).round();
                B.x && B.y ? (B = x._limitOffset(B, x.options.maxBounds), h.Util.requestAnimFrame(function() {
                    x.panBy(B, {duration: m, easeLinearity: w, noMoveStart: !0})
                })) : x.fire("moveend")
            }
        }}), h.Map.addInitHook("addHandler", "dragging", h.Map.Drag), h.Map.mergeOptions({doubleClickZoom: !0}), h.Map.DoubleClickZoom = h.Handler.extend({addHooks: function() {
            this._map.on("dblclick", this._onDoubleClick, this)
        }, removeHooks: function() {
            this._map.off("dblclick", this._onDoubleClick, this)
        }, _onDoubleClick: function(k) {
            var l = this._map, a = l.getZoom() + (k.originalEvent.shiftKey ? -1 : 1);
            "center" === l.options.doubleClickZoom ? l.setZoom(a) : l.setZoomAround(k.containerPoint, a)
        }}), h.Map.addInitHook("addHandler", "doubleClickZoom", h.Map.DoubleClickZoom), h.Map.mergeOptions({scrollWheelZoom: !0}), h.Map.ScrollWheelZoom = h.Handler.extend({addHooks: function() {
            h.DomEvent.on(this._map._container, "mousewheel", this._onWheelScroll, this), h.DomEvent.on(this._map._container, "MozMousePixelScroll", h.DomEvent.preventDefault), this._delta = 0
        }, removeHooks: function() {
            h.DomEvent.off(this._map._container, "mousewheel", this._onWheelScroll), h.DomEvent.off(this._map._container, "MozMousePixelScroll", h.DomEvent.preventDefault)
        }, _onWheelScroll: function(k) {
            var l = h.DomEvent.getWheelDelta(k);
            this._delta += l, this._lastMousePos = this._map.mouseEventToContainerPoint(k), this._startTime || (this._startTime = +new Date);
            var a = Math.max(40 - (+new Date - this._startTime), 0);
            clearTimeout(this._timer), this._timer = setTimeout(h.bind(this._performZoom, this), a), h.DomEvent.preventDefault(k), h.DomEvent.stopPropagation(k)
        }, _performZoom: function() {
            var k = this._map, l = this._delta, a = k.getZoom();
            l = l > 0 ? Math.ceil(l) : Math.floor(l), l = Math.max(Math.min(l, 4), -4), l = k._limitZoom(a + l) - a, this._delta = 0, this._startTime = null, l && ("center" === k.options.scrollWheelZoom ? k.setZoom(a + l) : k.setZoomAround(this._lastMousePos, a + l))
        }}), h.Map.addInitHook("addHandler", "scrollWheelZoom", h.Map.ScrollWheelZoom), h.extend(h.DomEvent, {_touchstart: h.Browser.msPointer ? "MSPointerDown" : h.Browser.pointer ? "pointerdown" : "touchstart", _touchend: h.Browser.msPointer ? "MSPointerUp" : h.Browser.pointer ? "pointerup" : "touchend", addDoubleTapListener: function(D, w, o) {
            function E(l) {
                var m;
                if (h.Browser.pointer ? (B.push(l.pointerId), m = B.length) : m = l.touches.length, !(m > 1)) {
                    var a = Date.now(), p = a - (e || a);
                    x = l.touches ? l.touches[0] : l, v = p > 0 && C >= p, e = a
                }
            }
            function A(l) {
                if (h.Browser.pointer) {
                    var p = B.indexOf(l.pointerId);
                    if (-1 === p) {
                        return
                    }
                    B.splice(p, 1)
                }
                if (v) {
                    if (h.Browser.pointer) {
                        var r, m = {};
                        for (var i in x) {
                            r = x[i], m[i] = "function" == typeof r ? r.bind(x) : r
                        }
                        x = m
                    }
                    x.type = "dblclick", w(x), e = null
                }
            }
            var e, x, v = !1, C = 250, z = "_leaflet_", y = this._touchstart, k = this._touchend, B = [];
            D[z + y + o] = E, D[z + k + o] = A;
            var q = h.Browser.pointer ? g.documentElement : D;
            return D.addEventListener(y, E, !1), q.addEventListener(k, A, !1), h.Browser.pointer && q.addEventListener(h.DomEvent.POINTER_CANCEL, A, !1), this
        }, removeDoubleTapListener: function(e, a) {
            var k = "_leaflet_";
            return e.removeEventListener(this._touchstart, e[k + this._touchstart + a], !1), (h.Browser.pointer ? g.documentElement : e).removeEventListener(this._touchend, e[k + this._touchend + a], !1), h.Browser.pointer && g.documentElement.removeEventListener(h.DomEvent.POINTER_CANCEL, e[k + this._touchend + a], !1), this
        }}), h.extend(h.DomEvent, {POINTER_DOWN: h.Browser.msPointer ? "MSPointerDown" : "pointerdown", POINTER_MOVE: h.Browser.msPointer ? "MSPointerMove" : "pointermove", POINTER_UP: h.Browser.msPointer ? "MSPointerUp" : "pointerup", POINTER_CANCEL: h.Browser.msPointer ? "MSPointerCancel" : "pointercancel", _pointers: [], _pointerDocumentListener: !1, addPointerListener: function(k, l, a, m) {
            switch (l) {
                case"touchstart":
                    return this.addPointerListenerStart(k, l, a, m);
                case"touchend":
                    return this.addPointerListenerEnd(k, l, a, m);
                case"touchmove":
                    return this.addPointerListenerMove(k, l, a, m);
                default:
                    throw"Unknown touch event type"
                }
        }, addPointerListenerStart: function(o, m, v, q) {
            var k = "_leaflet_", u = this._pointers, p = function(l) {
                h.DomEvent.preventDefault(l);
                for (var n = !1, a = 0; a < u.length; a++) {
                    if (u[a].pointerId === l.pointerId) {
                        n = !0;
                        break
                    }
                }
                n || u.push(l), l.touches = u.slice(), l.changedTouches = [l], v(l)
            };
            if (o[k + "touchstart" + q] = p, o.addEventListener(this.POINTER_DOWN, p, !1), !this._pointerDocumentListener) {
                var e = function(a) {
                    for (var i = 0; i < u.length; i++) {
                        if (u[i].pointerId === a.pointerId) {
                            u.splice(i, 1);
                            break
                        }
                    }
                };
                g.documentElement.addEventListener(this.POINTER_UP, e, !1), g.documentElement.addEventListener(this.POINTER_CANCEL, e, !1), this._pointerDocumentListener = !0
            }
            return this
        }, addPointerListenerMove: function(m, q, l, u) {
            function r(a) {
                if (a.pointerType !== a.MSPOINTER_TYPE_MOUSE && "mouse" !== a.pointerType || 0 !== a.buttons) {
                    for (var i = 0; i < k.length; i++) {
                        if (k[i].pointerId === a.pointerId) {
                            k[i] = a;
                            break
                        }
                    }
                    a.touches = k.slice(), a.changedTouches = [a], l(a)
                }
            }
            var p = "_leaflet_", k = this._pointers;
            return m[p + "touchmove" + u] = r, m.addEventListener(this.POINTER_MOVE, r, !1), this
        }, addPointerListenerEnd: function(m, q, l, u) {
            var r = "_leaflet_", p = this._pointers, k = function(a) {
                for (var i = 0; i < p.length; i++) {
                    if (p[i].pointerId === a.pointerId) {
                        p.splice(i, 1);
                        break
                    }
                }
                a.touches = p.slice(), a.changedTouches = [a], l(a)
            };
            return m[r + "touchend" + u] = k, m.addEventListener(this.POINTER_UP, k, !1), m.addEventListener(this.POINTER_CANCEL, k, !1), this
        }, removePointerListener: function(k, l, a) {
            var p = "_leaflet_", m = k[p + l + a];
            switch (l) {
                case"touchstart":
                    k.removeEventListener(this.POINTER_DOWN, m, !1);
                    break;
                case"touchmove":
                    k.removeEventListener(this.POINTER_MOVE, m, !1);
                    break;
                case"touchend":
                    k.removeEventListener(this.POINTER_UP, m, !1), k.removeEventListener(this.POINTER_CANCEL, m, !1)
            }
            return this
        }}), h.Map.mergeOptions({touchZoom: h.Browser.touch && !h.Browser.android23, bounceAtZoomLimits: !0}), h.Map.TouchZoom = h.Handler.extend({addHooks: function() {
            h.DomEvent.on(this._map._container, "touchstart", this._onTouchStart, this)
        }, removeHooks: function() {
            h.DomEvent.off(this._map._container, "touchstart", this._onTouchStart, this)
        }, _onTouchStart: function(l) {
            var k = this._map;
            if (l.touches && 2 === l.touches.length && !k._animatingZoom && !this._zooming) {
                var o = k.mouseEventToLayerPoint(l.touches[0]), m = k.mouseEventToLayerPoint(l.touches[1]), e = k._getCenterLayerPoint();
                this._startCenter = o.add(m)._divideBy(2), this._startDist = o.distanceTo(m), this._moved = !1, this._zooming = !0, this._centerOffset = e.subtract(this._startCenter), k._panAnim && k._panAnim.stop(), h.DomEvent.on(g, "touchmove", this._onTouchMove, this).on(g, "touchend", this._onTouchEnd, this), h.DomEvent.preventDefault(l)
            }
        }, _onTouchMove: function(k) {
            var l = this._map;
            if (k.touches && 2 === k.touches.length && this._zooming) {
                var a = l.mouseEventToLayerPoint(k.touches[0]), m = l.mouseEventToLayerPoint(k.touches[1]);
                this._scale = a.distanceTo(m) / this._startDist, this._delta = a._add(m)._divideBy(2)._subtract(this._startCenter), 1 !== this._scale && (l.options.bounceAtZoomLimits || !(l.getZoom() === l.getMinZoom() && this._scale < 1 || l.getZoom() === l.getMaxZoom() && this._scale > 1)) && (this._moved || (h.DomUtil.addClass(l._mapPane, "leaflet-touching"), l.fire("movestart").fire("zoomstart"), this._moved = !0), h.Util.cancelAnimFrame(this._animRequest), this._animRequest = h.Util.requestAnimFrame(this._updateOnMove, this, !0, this._map._container), h.DomEvent.preventDefault(k))
            }
        }, _updateOnMove: function() {
            var k = this._map, l = this._getScaleOrigin(), a = k.layerPointToLatLng(l), m = k.getScaleZoom(this._scale);
            k._animateZoom(a, m, this._startCenter, this._scale, this._delta, !1, !0)
        }, _onTouchEnd: function() {
            if (!this._moved || !this._zooming) {
                return void (this._zooming = !1)
            }
            var o = this._map;
            this._zooming = !1, h.DomUtil.removeClass(o._mapPane, "leaflet-touching"), h.Util.cancelAnimFrame(this._animRequest), h.DomEvent.off(g, "touchmove", this._onTouchMove).off(g, "touchend", this._onTouchEnd);
            var m = this._getScaleOrigin(), v = o.layerPointToLatLng(m), q = o.getZoom(), k = o.getScaleZoom(this._scale) - q, u = k > 0 ? Math.ceil(k) : Math.floor(k), p = o._limitZoom(q + u), e = o.getZoomScale(p) / this._scale;
            o._animateZoom(v, p, m, e)
        }, _getScaleOrigin: function() {
            var a = this._centerOffset.subtract(this._delta).divideBy(this._scale);
            return this._startCenter.add(a)
        }}), h.Map.addInitHook("addHandler", "touchZoom", h.Map.TouchZoom), h.Map.mergeOptions({tap: !0, tapTolerance: 15}), h.Map.Tap = h.Handler.extend({addHooks: function() {
            h.DomEvent.on(this._map._container, "touchstart", this._onDown, this)
        }, removeHooks: function() {
            h.DomEvent.off(this._map._container, "touchstart", this._onDown, this)
        }, _onDown: function(e) {
            if (e.touches) {
                if (h.DomEvent.preventDefault(e), this._fireClick = !0, e.touches.length > 1) {
                    return this._fireClick = !1, void clearTimeout(this._holdTimeout)
                }
                var a = e.touches[0], k = a.target;
                this._startPos = this._newPos = new h.Point(a.clientX, a.clientY), k.tagName && "a" === k.tagName.toLowerCase() && h.DomUtil.addClass(k, "leaflet-active"), this._holdTimeout = setTimeout(h.bind(function() {
                    this._isTapValid() && (this._fireClick = !1, this._onUp(), this._simulateEvent("contextmenu", a))
                }, this), 1000), h.DomEvent.on(g, "touchmove", this._onMove, this).on(g, "touchend", this._onUp, this)
            }
        }, _onUp: function(e) {
            if (clearTimeout(this._holdTimeout), h.DomEvent.off(g, "touchmove", this._onMove, this).off(g, "touchend", this._onUp, this), this._fireClick && e && e.changedTouches) {
                var a = e.changedTouches[0], k = a.target;
                k && k.tagName && "a" === k.tagName.toLowerCase() && h.DomUtil.removeClass(k, "leaflet-active"), this._isTapValid() && this._simulateEvent("click", a)
            }
        }, _isTapValid: function() {
            return this._newPos.distanceTo(this._startPos) <= this._map.options.tapTolerance
        }, _onMove: function(a) {
            var i = a.touches[0];
            this._newPos = new h.Point(i.clientX, i.clientY)
        }, _simulateEvent: function(a, k) {
            var e = g.createEvent("MouseEvents");
            e._simulated = !0, k.target._simulatedClick = !0, e.initMouseEvent(a, !0, !0, d, 1, k.screenX, k.screenY, k.clientX, k.clientY, !1, !1, !1, !1, 0, null), k.target.dispatchEvent(e)
        }}), h.Browser.touch && !h.Browser.pointer && h.Map.addInitHook("addHandler", "tap", h.Map.Tap), h.Map.mergeOptions({boxZoom: !0}), h.Map.BoxZoom = h.Handler.extend({initialize: function(a) {
            this._map = a, this._container = a._container, this._pane = a._panes.overlayPane, this._moved = !1
        }, addHooks: function() {
            h.DomEvent.on(this._container, "mousedown", this._onMouseDown, this)
        }, removeHooks: function() {
            h.DomEvent.off(this._container, "mousedown", this._onMouseDown), this._moved = !1
        }, moved: function() {
            return this._moved
        }, _onMouseDown: function(a) {
            return this._moved = !1, !a.shiftKey || 1 !== a.which && 1 !== a.button ? !1 : (h.DomUtil.disableTextSelection(), h.DomUtil.disableImageDrag(), this._startLayerPoint = this._map.mouseEventToLayerPoint(a), void h.DomEvent.on(g, "mousemove", this._onMouseMove, this).on(g, "mouseup", this._onMouseUp, this).on(g, "keydown", this._onKeyDown, this))
        }, _onMouseMove: function(m) {
            this._moved || (this._box = h.DomUtil.create("div", "leaflet-zoom-box", this._pane), h.DomUtil.setPosition(this._box, this._startLayerPoint), this._container.style.cursor = "crosshair", this._map.fire("boxzoomstart"));
            var p = this._startLayerPoint, l = this._box, q = this._map.mouseEventToLayerPoint(m), o = q.subtract(p), k = new h.Point(Math.min(q.x, p.x), Math.min(q.y, p.y));
            h.DomUtil.setPosition(l, k), this._moved = !0, l.style.width = Math.max(0, Math.abs(o.x) - 4) + "px", l.style.height = Math.max(0, Math.abs(o.y) - 4) + "px"
        }, _finish: function() {
            this._moved && (this._pane.removeChild(this._box), this._container.style.cursor = ""), h.DomUtil.enableTextSelection(), h.DomUtil.enableImageDrag(), h.DomEvent.off(g, "mousemove", this._onMouseMove).off(g, "mouseup", this._onMouseUp).off(g, "keydown", this._onKeyDown)
        }, _onMouseUp: function(k) {
            this._finish();
            var l = this._map, a = l.mouseEventToLayerPoint(k);
            if (!this._startLayerPoint.equals(a)) {
                var m = new h.LatLngBounds(l.layerPointToLatLng(this._startLayerPoint), l.layerPointToLatLng(a));
                l.fitBounds(m), l.fire("boxzoomend", {boxZoomBounds: m})
            }
        }, _onKeyDown: function(a) {
            27 === a.keyCode && this._finish()
        }}), h.Map.addInitHook("addHandler", "boxZoom", h.Map.BoxZoom), h.Map.mergeOptions({keyboard: !0, keyboardPanOffset: 80, keyboardZoomOffset: 1}), h.Map.Keyboard = h.Handler.extend({keyCodes: {left: [37], right: [39], down: [40], up: [38], zoomIn: [187, 107, 61, 171], zoomOut: [189, 109, 173]}, initialize: function(a) {
            this._map = a, this._setPanOffset(a.options.keyboardPanOffset), this._setZoomOffset(a.options.keyboardZoomOffset)
        }, addHooks: function() {
            var a = this._map._container;
            -1 === a.tabIndex && (a.tabIndex = "0"), h.DomEvent.on(a, "focus", this._onFocus, this).on(a, "blur", this._onBlur, this).on(a, "mousedown", this._onMouseDown, this), this._map.on("focus", this._addHooks, this).on("blur", this._removeHooks, this)
        }, removeHooks: function() {
            this._removeHooks();
            var a = this._map._container;
            h.DomEvent.off(a, "focus", this._onFocus, this).off(a, "blur", this._onBlur, this).off(a, "mousedown", this._onMouseDown, this), this._map.off("focus", this._addHooks, this).off("blur", this._removeHooks, this)
        }, _onMouseDown: function() {
            if (!this._focused) {
                var a = g.body, l = g.documentElement, k = a.scrollTop || l.scrollTop, e = a.scrollLeft || l.scrollLeft;
                this._map._container.focus(), d.scrollTo(e, k)
            }
        }, _onFocus: function() {
            this._focused = !0, this._map.fire("focus")
        }, _onBlur: function() {
            this._focused = !1, this._map.fire("blur")
        }, _setPanOffset: function(k) {
            var l, a, p = this._panKeys = {}, m = this.keyCodes;
            for (l = 0, a = m.left.length; a > l; l++) {
                p[m.left[l]] = [-1 * k, 0]
            }
            for (l = 0, a = m.right.length; a > l; l++) {
                p[m.right[l]] = [k, 0]
            }
            for (l = 0, a = m.down.length; a > l; l++) {
                p[m.down[l]] = [0, k]
            }
            for (l = 0, a = m.up.length; a > l; l++) {
                p[m.up[l]] = [0, -1 * k]
            }
        }, _setZoomOffset: function(k) {
            var l, a, p = this._zoomKeys = {}, m = this.keyCodes;
            for (l = 0, a = m.zoomIn.length; a > l; l++) {
                p[m.zoomIn[l]] = k
            }
            for (l = 0, a = m.zoomOut.length; a > l; l++) {
                p[m.zoomOut[l]] = -k
            }
        }, _addHooks: function() {
            h.DomEvent.on(g, "keydown", this._onKeyDown, this)
        }, _removeHooks: function() {
            h.DomEvent.off(g, "keydown", this._onKeyDown, this)
        }, _onKeyDown: function(k) {
            var l = k.keyCode, a = this._map;
            if (l in this._panKeys) {
                if (a._panAnim && a._panAnim._inProgress) {
                    return
                }
                a.panBy(this._panKeys[l]), a.options.maxBounds && a.panInsideBounds(a.options.maxBounds)
            } else {
                if (!(l in this._zoomKeys)) {
                    return
                }
                a.setZoom(a.getZoom() + this._zoomKeys[l])
            }
            h.DomEvent.stop(k)
        }}), h.Map.addInitHook("addHandler", "keyboard", h.Map.Keyboard), h.Handler.MarkerDrag = h.Handler.extend({initialize: function(a) {
            this._marker = a
        }, addHooks: function() {
            var a = this._marker._icon;
            this._draggable || (this._draggable = new h.Draggable(a, a)), this._draggable.on("dragstart", this._onDragStart, this).on("drag", this._onDrag, this).on("dragend", this._onDragEnd, this), this._draggable.enable(), h.DomUtil.addClass(this._marker._icon, "leaflet-marker-draggable")
        }, removeHooks: function() {
            this._draggable.off("dragstart", this._onDragStart, this).off("drag", this._onDrag, this).off("dragend", this._onDragEnd, this), this._draggable.disable(), h.DomUtil.removeClass(this._marker._icon, "leaflet-marker-draggable")
        }, moved: function() {
            return this._draggable && this._draggable._moved
        }, _onDragStart: function() {
            this._marker.closePopup().fire("movestart").fire("dragstart")
        }, _onDrag: function() {
            var k = this._marker, l = k._shadow, a = h.DomUtil.getPosition(k._icon), m = k._map.layerPointToLatLng(a);
            l && h.DomUtil.setPosition(l, a), k._latlng = m, k.fire("move", {latlng: m}).fire("drag")
        }, _onDragEnd: function(a) {
            this._marker.fire("moveend").fire("dragend", a)
        }}), h.Control = h.Class.extend({options: {position: "topright"}, initialize: function(a) {
            h.setOptions(this, a)
        }, getPosition: function() {
            return this.options.position
        }, setPosition: function(a) {
            var i = this._map;
            return i && i.removeControl(this), this.options.position = a, i && i.addControl(this), this
        }, getContainer: function() {
            return this._container
        }, addTo: function(k) {
            this._map = k;
            var l = this._container = this.onAdd(k), a = this.getPosition(), m = k._controlCorners[a];
            return h.DomUtil.addClass(l, "leaflet-control"), -1 !== a.indexOf("bottom") ? m.insertBefore(l, m.firstChild) : m.appendChild(l), this
        }, removeFrom: function(k) {
            var l = this.getPosition(), a = k._controlCorners[l];
            return a.removeChild(this._container), this._map = null, this.onRemove && this.onRemove(k), this
        }, _refocusOnMap: function() {
            this._map && this._map.getContainer().focus()
        }}), h.control = function(a) {
        return new h.Control(a)
    }, h.Map.include({addControl: function(a) {
            return a.addTo(this), this
        }, removeControl: function(a) {
            return a.removeFrom(this), this
        }, _initControlPos: function() {
            function k(i, n) {
                var e = a + i + " " + a + n;
                l[i + n] = h.DomUtil.create("div", e, m)
            }
            var l = this._controlCorners = {}, a = "leaflet-", m = this._controlContainer = h.DomUtil.create("div", a + "control-container", this._container);
            k("top", "left"), k("top", "right"), k("bottom", "left"), k("bottom", "right")
        }, _clearControlPos: function() {
            this._container.removeChild(this._controlContainer)
        }}), h.Control.Zoom = h.Control.extend({options: {position: "topleft", zoomInText: "+", zoomInTitle: "Zoom in", zoomOutText: "-", zoomOutTitle: "Zoom out"}, onAdd: function(k) {
            var l = "leaflet-control-zoom", a = h.DomUtil.create("div", l + " leaflet-bar");
            return this._map = k, this._zoomInButton = this._createButton(this.options.zoomInText, this.options.zoomInTitle, l + "-in", a, this._zoomIn, this), this._zoomOutButton = this._createButton(this.options.zoomOutText, this.options.zoomOutTitle, l + "-out", a, this._zoomOut, this), this._updateDisabled(), k.on("zoomend zoomlevelschange", this._updateDisabled, this), a
        }, onRemove: function(a) {
            a.off("zoomend zoomlevelschange", this._updateDisabled, this)
        }, _zoomIn: function(a) {
            this._map.zoomIn(a.shiftKey ? 3 : 1)
        }, _zoomOut: function(a) {
            this._map.zoomOut(a.shiftKey ? 3 : 1)
        }, _createButton: function(m, u, l, v, p, k) {
            var q = h.DomUtil.create("a", l, v);
            q.innerHTML = m, q.href = "#", q.title = u;
            var o = h.DomEvent.stopPropagation;
            return h.DomEvent.on(q, "click", o).on(q, "mousedown", o).on(q, "dblclick", o).on(q, "click", h.DomEvent.preventDefault).on(q, "click", p, k).on(q, "click", this._refocusOnMap, k), q
        }, _updateDisabled: function() {
            var a = this._map, i = "leaflet-disabled";
            h.DomUtil.removeClass(this._zoomInButton, i), h.DomUtil.removeClass(this._zoomOutButton, i), a._zoom === a.getMinZoom() && h.DomUtil.addClass(this._zoomOutButton, i), a._zoom === a.getMaxZoom() && h.DomUtil.addClass(this._zoomInButton, i)
        }}), h.Map.mergeOptions({zoomControl: !0}), h.Map.addInitHook(function() {
        this.options.zoomControl && (this.zoomControl = new h.Control.Zoom, this.addControl(this.zoomControl))
    }), h.control.zoom = function(a) {
        return new h.Control.Zoom(a)
    }, h.Control.Attribution = h.Control.extend({options: {position: "bottomright", prefix: '<div class="leaflet-control-attribution">'}, initialize: function(a) {
            h.setOptions(this, a), this._attributions = {}
        }, onAdd: function(a) {
            this._container = h.DomUtil.create("div", ""), h.DomEvent.disableClickPropagation(this._container);
            this._water = '<div><img src="http://maps.mapmyindia.com/images/watermark.png" alt="MapmyIndia.com" title="MapmyIndia.com"></div>';
            for (var i in a._layers) {
                a._layers[i].getAttribution && this.addAttribution(a._layers[i].getAttribution())
            }
            return a.on("layeradd", this._onLayerAdd, this).on("layerremove", this._onLayerRemove, this), this._update(), this._container
        }, onRemove: function(a) {
            a.off("layeradd", this._onLayerAdd).off("layerremove", this._onLayerRemove)
        }, setPrefix: function(a) {
            return this.options.prefix = a, this._update(), this
        }, addAttribution: function(a) {
            return a ? (this._attributions[a] || (this._attributions[a] = 0), this._attributions[a]++, this._update(), this) : void 0
        }, removeAttribution: function(a) {
            return a ? (this._attributions[a] && (this._attributions[a]--, this._update()), this) : void 0
        }, _update: function() {
            if (this._map) {
                var k = [], a = [], l = [];
                for (var m in this._attributions) {
                    this._attributions[m] && k.push(m)
                }
                this.options.prefix && a.push(this.options.prefix), k.length && a.push(k.join(", ")), l.push(this._water), l.push(a.join("")), this._container.innerHTML = l.join("")
            }
        }, _onLayerAdd: function(a) {
            a.layer.getAttribution && this.addAttribution(a.layer.getAttribution())
        }, _onLayerRemove: function(a) {
            a.layer.getAttribution && this.removeAttribution(a.layer.getAttribution())
        }}), h.Map.mergeOptions({attributionControl: !0}), h.Map.addInitHook(function() {
        this.options.attributionControl && (this.attributionControl = (new h.Control.Attribution).addTo(this))
    }), h.control.attribution = function(a) {
        return new h.Control.Attribution(a)
    }, h.Control.Scale = h.Control.extend({options: {position: "bottomleft", maxWidth: 100, metric: !0, imperial: !0, updateWhenIdle: !1}, onAdd: function(k) {
            this._map = k;
            var l = "leaflet-control-scale", a = h.DomUtil.create("div", l), m = this.options;
            return this._addScales(m, l, a), k.on(m.updateWhenIdle ? "moveend" : "move", this._update, this), k.whenReady(this._update, this), a
        }, onRemove: function(a) {
            a.off(this.options.updateWhenIdle ? "moveend" : "move", this._update, this)
        }, _addScales: function(k, l, a) {
            k.metric && (this._mScale = h.DomUtil.create("div", l + "-line", a)), k.imperial && (this._iScale = h.DomUtil.create("div", l + "-line", a))
        }, _update: function() {
            var m = this._map.getBounds(), q = m.getCenter().lat, l = 6378137 * Math.PI * Math.cos(q * Math.PI / 180), u = l * (m.getNorthEast().lng - m.getSouthWest().lng) / 180, r = this._map.getSize(), p = this.options, k = 0;
            r.x > 0 && (k = u * (p.maxWidth / r.x)), this._updateScales(p, k)
        }, _updateScales: function(a, i) {
            a.metric && i && this._updateMetric(i), a.imperial && i && this._updateImperial(i)
        }, _updateMetric: function(a) {
            var i = this._getRoundNum(a);
            this._mScale.style.width = this._getScaleWidth(i / a) + "px", this._mScale.innerHTML = 1000 > i ? i + " m" : i / 1000 + " km"
        }, _updateImperial: function(k) {
            var m, a, q, p = 3.2808399 * k, l = this._iScale;
            p > 5280 ? (m = p / 5280, a = this._getRoundNum(m), l.style.width = this._getScaleWidth(a / m) + "px", l.innerHTML = a + " mi") : (q = this._getRoundNum(p), l.style.width = this._getScaleWidth(q / p) + "px", l.innerHTML = q + " ft")
        }, _getScaleWidth: function(a) {
            return Math.round(this.options.maxWidth * a) - 10
        }, _getRoundNum: function(k) {
            var l = Math.pow(10, (Math.floor(k) + "").length - 1), a = k / l;
            return a = a >= 10 ? 10 : a >= 5 ? 5 : a >= 3 ? 3 : a >= 2 ? 2 : 1, l * a
        }}), h.control.scale = function(a) {
        return new h.Control.Scale(a)
    }, h.Control.Layers = h.Control.extend({options: {collapsed: !0, position: "topright", autoZIndex: !0}, initialize: function(k, l, a) {
            h.setOptions(this, a), this._layers = {}, this._lastZIndex = 0, this._handlingClick = !1;
            for (var m in k) {
                this._addLayer(k[m], m)
            }
            for (m in l) {
                this._addLayer(l[m], m, !0)
            }
        }, onAdd: function(a) {
            return this._initLayout(), this._update(), a.on("layeradd", this._onLayerChange, this).on("layerremove", this._onLayerChange, this), this._container
        }, onRemove: function(a) {
            a.off("layeradd", this._onLayerChange, this).off("layerremove", this._onLayerChange, this)
        }, addBaseLayer: function(a, i) {
            return this._addLayer(a, i), this._update(), this
        }, addOverlay: function(a, i) {
            return this._addLayer(a, i, !0), this._update(), this
        }, removeLayer: function(a) {
            var i = h.stamp(a);
            return delete this._layers[i], this._update(), this
        }, _initLayout: function() {
            var k = "leaflet-control-layers", l = this._container = h.DomUtil.create("div", k);
            l.setAttribute("aria-haspopup", !0), h.Browser.touch ? h.DomEvent.on(l, "click", h.DomEvent.stopPropagation) : h.DomEvent.disableClickPropagation(l).disableScrollPropagation(l);
            var a = this._form = h.DomUtil.create("form", k + "-list");
            if (this.options.collapsed) {
                h.Browser.android || h.DomEvent.on(l, "mouseover", this._expand, this).on(l, "mouseout", this._collapse, this);
                var m = this._layersLink = h.DomUtil.create("a", k + "-toggle", l);
                m.href = "#", m.title = "Layers", h.Browser.touch ? h.DomEvent.on(m, "click", h.DomEvent.stop).on(m, "click", this._expand, this) : h.DomEvent.on(m, "focus", this._expand, this), h.DomEvent.on(a, "click", function() {
                    setTimeout(h.bind(this._onInputClick, this), 0)
                }, this), this._map.on("click", this._collapse, this)
            } else {
                this._expand()
            }
            this._baseLayersList = h.DomUtil.create("div", k + "-base", a), this._separator = h.DomUtil.create("div", k + "-separator", a), this._overlaysList = h.DomUtil.create("div", k + "-overlays", a), l.appendChild(a)
        }, _addLayer: function(k, l, a) {
            var m = h.stamp(k);
            this._layers[m] = {layer: k, name: l, overlay: a}, this.options.autoZIndex && k.setZIndex && (this._lastZIndex++, k.setZIndex(this._lastZIndex))
        }, _update: function() {
            if (this._container) {
                this._baseLayersList.innerHTML = "", this._overlaysList.innerHTML = "";
                var k, l, a = !1, m = !1;
                for (k in this._layers) {
                    l = this._layers[k], this._addItem(l), m = m || l.overlay, a = a || !l.overlay
                }
                this._separator.style.display = m && a ? "" : "none"
            }
        }, _onLayerChange: function(k) {
            var l = this._layers[h.stamp(k.layer)];
            if (l) {
                this._handlingClick || this._update();
                var a = l.overlay ? "layeradd" === k.type ? "overlayadd" : "overlayremove" : "layeradd" === k.type ? "baselayerchange" : null;
                a && this._map.fire(a, l)
            }
        }, _createRadioElement: function(e, a) {
            var l = '<input type="radio" class="leaflet-control-layers-selector" name="' + e + '"';
            a && (l += ' checked="checked"'), l += "/>";
            var k = g.createElement("div");
            return k.innerHTML = l, k.firstChild
        }, _addItem: function(l) {
            var k, p = g.createElement("label"), m = this._map.hasLayer(l.layer);
            l.overlay ? (k = g.createElement("input"), k.type = "checkbox", k.className = "leaflet-control-layers-selector", k.defaultChecked = m) : k = this._createRadioElement("leaflet-base-layers", m), k.layerId = h.stamp(l.layer), h.DomEvent.on(k, "click", this._onInputClick, this);
            var e = g.createElement("span");
            e.innerHTML = " " + l.name, p.appendChild(k), p.appendChild(e);
            var o = l.overlay ? this._overlaysList : this._baseLayersList;
            return o.appendChild(p), p
        }, _onInputClick: function() {
            var k, l, a, p = this._form.getElementsByTagName("input"), m = p.length;
            for (this._handlingClick = !0, k = 0; m > k; k++) {
                l = p[k], a = this._layers[l.layerId], l.checked && !this._map.hasLayer(a.layer) ? this._map.addLayer(a.layer) : !l.checked && this._map.hasLayer(a.layer) && this._map.removeLayer(a.layer)
            }
            this._handlingClick = !1, this._refocusOnMap()
        }, _expand: function() {
            h.DomUtil.addClass(this._container, "leaflet-control-layers-expanded")
        }, _collapse: function() {
            this._container.className = this._container.className.replace(" leaflet-control-layers-expanded", "")
        }}), h.control.layers = function(k, l, a) {
        return new h.Control.Layers(k, l, a)
    }, h.PosAnimation = h.Class.extend({includes: h.Mixin.Events, run: function(k, l, a, m) {
            this.stop(), this._el = k, this._inProgress = !0, this._newPos = l, this.fire("start"), k.style[h.DomUtil.TRANSITION] = "all " + (a || 0.25) + "s cubic-bezier(0,0," + (m || 0.5) + ",1)", h.DomEvent.on(k, h.DomUtil.TRANSITION_END, this._onTransitionEnd, this), h.DomUtil.setPosition(k, l), h.Util.falseFn(k.offsetWidth), this._stepTimer = setInterval(h.bind(this._onStep, this), 50)
        }, stop: function() {
            this._inProgress && (h.DomUtil.setPosition(this._el, this._getPos()), this._onTransitionEnd(), h.Util.falseFn(this._el.offsetWidth))
        }, _onStep: function() {
            var a = this._getPos();
            return a ? (this._el._leaflet_pos = a, void this.fire("step")) : void this._onTransitionEnd()
        }, _transformRe: /([-+]?(?:\d*\.)?\d+)\D*, ([-+]?(?:\d*\.)?\d+)\D*\)/, _getPos: function() {
            var o, l, p, m = this._el, k = d.getComputedStyle(m);
            if (h.Browser.any3d) {
                if (p = k[h.DomUtil.TRANSFORM].match(this._transformRe), !p) {
                    return
                }
                o = parseFloat(p[1]), l = parseFloat(p[2])
            } else {
                o = parseFloat(k.left), l = parseFloat(k.top)
            }
            return new h.Point(o, l, !0)
        }, _onTransitionEnd: function() {
            h.DomEvent.off(this._el, h.DomUtil.TRANSITION_END, this._onTransitionEnd, this), this._inProgress && (this._inProgress = !1, this._el.style[h.DomUtil.TRANSITION] = "", this._el._leaflet_pos = this._newPos, clearInterval(this._stepTimer), this.fire("step").fire("end"))
        }}), h.Map.include({setView: function(a, k, l) {
            if (k = k === c ? this._zoom : this._limitZoom(k), a = this._limitCenter(h.latLng(a), k, this.options.maxBounds), l = l || {}, this._panAnim && this._panAnim.stop(), this._loaded && !l.reset && l !== !0) {
                l.animate !== c && (l.zoom = h.extend({animate: l.animate}, l.zoom), l.pan = h.extend({animate: l.animate}, l.pan));
                var i = this._zoom !== k ? this._tryAnimatedZoom && this._tryAnimatedZoom(a, k, l.zoom) : this._tryAnimatedPan(a, l.pan);
                if (i) {
                    return clearTimeout(this._sizeTimer), this
                }
            }
            return this._resetView(a, k), this
        }, panBy: function(k, l) {
            if (k = h.point(k).round(), l = l || {}, !k.x && !k.y) {
                return this
            }
            if (this._panAnim || (this._panAnim = new h.PosAnimation, this._panAnim.on({step: this._onPanTransitionStep, end: this._onPanTransitionEnd}, this)), l.noMoveStart || this.fire("movestart"), l.animate !== !1) {
                h.DomUtil.addClass(this._mapPane, "leaflet-pan-anim");
                var a = this._getMapPanePos().subtract(k);
                this._panAnim.run(this._mapPane, a, l.duration || 0.25, l.easeLinearity)
            } else {
                this._rawPanBy(k), this.fire("move").fire("moveend")
            }
            return this
        }, _onPanTransitionStep: function() {
            this.fire("move")
        }, _onPanTransitionEnd: function() {
            h.DomUtil.removeClass(this._mapPane, "leaflet-pan-anim"), this.fire("moveend")
        }, _tryAnimatedPan: function(k, l) {
            var a = this._getCenterOffset(k)._floor();
            return(l && l.animate) === !0 || this.getSize().contains(a) ? (this.panBy(a, l), !0) : !1
        }}), h.PosAnimation = h.DomUtil.TRANSITION ? h.PosAnimation : h.PosAnimation.extend({run: function(k, l, a, m) {
            this.stop(), this._el = k, this._inProgress = !0, this._duration = a || 0.25, this._easeOutPower = 1 / Math.max(m || 0.5, 0.2), this._startPos = h.DomUtil.getPosition(k), this._offset = l.subtract(this._startPos), this._startTime = +new Date, this.fire("start"), this._animate()
        }, stop: function() {
            this._inProgress && (this._step(), this._complete())
        }, _animate: function() {
            this._animId = h.Util.requestAnimFrame(this._animate, this), this._step()
        }, _step: function() {
            var a = +new Date - this._startTime, i = 1000 * this._duration;
            i > a ? this._runFrame(this._easeOut(a / i)) : (this._runFrame(1), this._complete())
        }, _runFrame: function(a) {
            var i = this._startPos.add(this._offset.multiplyBy(a));
            h.DomUtil.setPosition(this._el, i), this.fire("step")
        }, _complete: function() {
            h.Util.cancelAnimFrame(this._animId), this._inProgress = !1, this.fire("end")
        }, _easeOut: function(a) {
            return 1 - Math.pow(1 - a, this._easeOutPower)
        }}), h.Map.mergeOptions({zoomAnimation: !0, zoomAnimationThreshold: 4}), h.DomUtil.TRANSITION && h.Map.addInitHook(function() {
        this._zoomAnimated = this.options.zoomAnimation && h.DomUtil.TRANSITION && h.Browser.any3d && !h.Browser.android23 && !h.Browser.mobileOpera, this._zoomAnimated && h.DomEvent.on(this._mapPane, h.DomUtil.TRANSITION_END, this._catchTransitionEnd, this)
    }), h.Map.include(h.DomUtil.TRANSITION ? {_catchTransitionEnd: function(a) {
            this._animatingZoom && a.propertyName.indexOf("transform") >= 0 && this._onZoomTransitionEnd()
        }, _nothingToAnimate: function() {
            return !this._container.getElementsByClassName("leaflet-zoom-animated").length
        }, _tryAnimatedZoom: function(k, m, a) {
            if (this._animatingZoom) {
                return !0
            }
            if (a = a || {}, !this._zoomAnimated || a.animate === !1 || this._nothingToAnimate() || Math.abs(m - this._zoom) > this.options.zoomAnimationThreshold) {
                return !1
            }
            var q = this.getZoomScale(m), p = this._getCenterOffset(k)._divideBy(1 - 1 / q), l = this._getCenterLayerPoint()._add(p);
            return a.animate === !0 || this.getSize().contains(p) ? (this.fire("movestart").fire("zoomstart"), this._animateZoom(k, m, l, q, null, !0), !0) : !1
        }, _animateZoom: function(m, q, l, u, o, k, p) {
            p || (this._animatingZoom = !0), h.DomUtil.addClass(this._mapPane, "leaflet-zoom-anim"), this._animateToCenter = m, this._animateToZoom = q, h.Draggable && (h.Draggable._disabled = !0), h.Util.requestAnimFrame(function() {
                this.fire("zoomanim", {center: m, zoom: q, origin: l, scale: u, delta: o, backwards: k})
            }, this)
        }, _onZoomTransitionEnd: function() {
            this._animatingZoom = !1, h.DomUtil.removeClass(this._mapPane, "leaflet-zoom-anim"), this._resetView(this._animateToCenter, this._animateToZoom, !0, !0), h.Draggable && (h.Draggable._disabled = !1)
        }} : {}), h.TileLayer.include({_animateZoom: function(k) {
            this._animating || (this._animating = !0, this._prepareBgBuffer());
            var m = this._bgBuffer, a = h.DomUtil.TRANSFORM, o = k.delta ? h.DomUtil.getTranslateString(k.delta) : m.style[a], l = h.DomUtil.getScaleString(k.scale, k.origin);
            m.style[a] = k.backwards ? l + " " + o : o + " " + l
        }, _endZoomAnim: function() {
            var a = this._tileContainer, i = this._bgBuffer;
            a.style.visibility = "", a.parentNode.appendChild(a), h.Util.falseFn(i.offsetWidth), this._animating = !1
        }, _clearBgBuffer: function() {
            var a = this._map;
            !a || a._animatingZoom || a.touchZoom._zooming || (this._bgBuffer.innerHTML = "", this._bgBuffer.style[h.DomUtil.TRANSFORM] = "")
        }, _prepareBgBuffer: function() {
            var k = this._tileContainer, l = this._bgBuffer, a = this._getLoadedTilesPercentage(l), m = this._getLoadedTilesPercentage(k);
            return l && a > 0.5 && 0.5 > m ? (k.style.visibility = "hidden", void this._stopLoadingImages(k)) : (l.style.visibility = "hidden", l.style[h.DomUtil.TRANSFORM] = "", this._tileContainer = l, l = this._bgBuffer = k, this._stopLoadingImages(l), void clearTimeout(this._clearBgBufferTimer))
        }, _getLoadedTilesPercentage: function(k) {
            var l, a, p = k.getElementsByTagName("img"), m = 0;
            for (l = 0, a = p.length; a > l; l++) {
                p[l].complete && m++
            }
            return m / a
        }, _stopLoadingImages: function(k) {
            var m, a, o, l = Array.prototype.slice.call(k.getElementsByTagName("img"));
            for (m = 0, a = l.length; a > m; m++) {
                o = l[m], o.complete || (o.onload = h.Util.falseFn, o.onerror = h.Util.falseFn, o.src = h.Util.emptyImageUrl, o.parentNode.removeChild(o))
            }
        }}), h.Map.include({_defaultLocateOptions: {watch: !1, setView: !1, maxZoom: 1 / 0, timeout: 10000, maximumAge: 0, enableHighAccuracy: !1}, locate: function(k) {
            if (k = this._locateOptions = h.extend(this._defaultLocateOptions, k), !navigator.geolocation) {
                return this._handleGeolocationError({code: 0, message: "Geolocation not supported."}), this
            }
            var l = h.bind(this._handleGeolocationResponse, this), a = h.bind(this._handleGeolocationError, this);
            return k.watch ? this._locationWatchId = navigator.geolocation.watchPosition(l, a, k) : navigator.geolocation.getCurrentPosition(l, a, k), this
        }, stopLocate: function() {
            return navigator.geolocation && navigator.geolocation.clearWatch(this._locationWatchId), this._locateOptions && (this._locateOptions.setView = !1), this
        }, _handleGeolocationError: function(k) {
            var l = k.code, a = k.message || (1 === l ? "permission denied" : 2 === l ? "position unavailable" : "timeout");
            this._locateOptions.setView && !this._loaded && this.fitWorld(), this.fire("locationerror", {code: l, message: "Geolocation error: " + a + "."})
        }, _handleGeolocationResponse: function(z) {
            var v = z.coords.latitude, p = z.coords.longitude, m = new h.LatLng(v, p), A = 180 * z.coords.accuracy / 40075017, x = A / Math.cos(h.LatLng.DEG_TO_RAD * v), k = h.latLngBounds([v - A, p - x], [v + A, p + x]), q = this._locateOptions;
            if (q.setView) {
                var o = Math.min(this.getBoundsZoom(k), q.maxZoom);
                this.setView(m, o)
            }
            var y = {latlng: m, bounds: k, timestamp: z.timestamp};
            for (var w in z.coords) {
                "number" == typeof z.coords[w] && (y[w] = z.coords[w])
            }
            this.fire("locationfound", y)
        }})
}(window, document);