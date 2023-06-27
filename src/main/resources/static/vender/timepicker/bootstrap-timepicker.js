/*! version : 4.17.37
 =========================================================
 bootstrap-datetimejs
 https://github.com/Eonasdan/bootstrap-datetimepicker
 Copyright (c) 2015 Jonathan Peterson
 =========================================================
 */
! function(a) {
    "use strict";
    if ("function" == typeof define && define.amd) define(["jquery", "moment"], a);
    else if ("object" == typeof exports) a(require("jquery"), require("moment"));
    else {
        if ("undefined" == typeof jQuery) throw "bootstrap-datetimepicker requires jQuery to be loaded first";
        if ("undefined" == typeof moment) throw "bootstrap-datetimepicker requires Moment.js to be loaded first";
        a(jQuery, moment)
    }
}(function(a, b) {
    "use strict";
    if (!b) throw new Error("bootstrap-datetimepicker requires Moment.js to be loaded first");
    var c = function(c, d) {
        var e, f, g, h, i, j, k, l = {},
            m = !0,
            n = !1,
            o = !1,
            p = 0,
            q = [{
                clsName: "days",
                navFnc: "M",
                navStep: 1
            }, {
                clsName: "months",
                navFnc: "y",
                navStep: 1
            }, {
                clsName: "years",
                navFnc: "y",
                navStep: 10
            }, {
                clsName: "decades",
                navFnc: "y",
                navStep: 100
            }],
            r = ["days", "months", "years", "decades"],
            s = ["top", "bottom", "auto"],
            t = ["left", "right", "auto"],
            u = ["default", "top", "bottom"],
            v = {
                up: 38,
                38: "up",
                down: 40,
                40: "down",
                left: 37,
                37: "left",
                right: 39,
                39: "right",
                tab: 9,
                9: "tab",
                escape: 27,
                27: "escape",
                enter: 13,
                13: "enter",
                pageUp: 33,
                33: "pageUp",
                pageDown: 34,
                34: "pageDown",
                shift: 16,
                16: "shift",
                control: 17,
                17: "control",
                space: 32,
                32: "space",
                t: 84,
                84: "t",
                "delete": 46,
                46: "delete"
            },
            w = {},
            x = function(a) {
                var c, e, f, g, h, i = !1;
                return void 0 !== b.tz && void 0 !== d.timeZone && null !== d.timeZone && "" !== d.timeZone && (i = !0), void 0 === a || null === a ? c = i ? b().tz(d.timeZone).startOf("d") : b().startOf("d") : i ? (e = b().tz(d.timeZone).utcOffset(), f = b(a, j, d.useStrict).utcOffset(), f !== e ? (g = b().tz(d.timeZone).format("Z"), h = b(a, j, d.useStrict).format("YYYY-MM-DD[T]HH:mm:ss") + g, c = b(h, j, d.useStrict).tz(d.timeZone)) : c = b(a, j, d.useStrict).tz(d.timeZone)) : c = b(a, j, d.useStrict), c
            },
            y = function(a) {
                if ("string" != typeof a || a.length > 1) throw new TypeError("isEnabled expects a single character string parameter");
                switch (a) {
                    case "y":
                        return -1 !== i.indexOf("Y");
                    case "M":
                        return -1 !== i.indexOf("M");
                    case "d":
                        return -1 !== i.toLowerCase().indexOf("d");
                    case "h":
                    case "H":
                        return -1 !== i.toLowerCase().indexOf("h");
                    case "m":
                        return -1 !== i.indexOf("m");
                    case "s":
                        return -1 !== i.indexOf("s");
                    default:
                        return !1
                }
            },
            z = function() {
                return y("h") || y("m") || y("s")
            },
            A = function() {
                return y("y") || y("M") || y("d")
            },
            B = function() {
                var b = a("<thead>").append(a("<tr>").append(a("<th>").addClass("prev").attr("data-action", "previous").append(a("<span>").addClass(d.icons.previous))).append(a("<th>").addClass("picker-switch").attr("data-action", "pickerSwitch").attr("colspan", d.calendarWeeks ? "6" : "5")).append(a("<th>").addClass("next").attr("data-action", "next").append(a("<span>").addClass(d.icons.next)))),
                    c = a("<tbody>").append(a("<tr>").append(a("<td>").attr("colspan", d.calendarWeeks ? "8" : "7")));
                return [a("<div>").addClass("datepicker-days").append(a("<table>").addClass("table-condensed").append(b).append(a("<tbody>"))), a("<div>").addClass("datepicker-months").append(a("<table>").addClass("table-condensed").append(b.clone()).append(c.clone())), a("<div>").addClass("datepicker-years").append(a("<table>").addClass("table-condensed").append(b.clone()).append(c.clone())), a("<div>").addClass("datepicker-decades").append(a("<table>").addClass("table-condensed").append(b.clone()).append(c.clone()))]
            },
            C = function() {
                var b = a("<tr>"),
                    c = a("<tr>"),
                    e = a("<tr>");
                return y("h") && (b.append(a("<td>").append(a("<a>").attr({
                    href: "#",
                    tabindex: "-1",
                    title: d.tooltips.incrementHour
                }).addClass("btn").attr("data-action", "incrementHours").append(a("<span>").addClass(d.icons.up)))), c.append(a("<td>").append(a("<span>").addClass("timepicker-hour").attr({
                    "data-time-component": "hours",
                    title: d.tooltips.pickHour
                }).attr("data-action", "showHours"))), e.append(a("<td>").append(a("<a>").attr({
                    href: "#",
                    tabindex: "-1",
                    title: d.tooltips.decrementHour
                }).addClass("btn").attr("data-action", "decrementHours").append(a("<span>").addClass(d.icons.down))))), y("m") && (y("h") && (b.append(a("<td>").addClass("separator")), c.append(a("<td>").addClass("separator").html(":")), e.append(a("<td>").addClass("separator"))), b.append(a("<td>").append(a("<a>").attr({
                    href: "#",
                    tabindex: "-1",
                    title: d.tooltips.incrementMinute
                }).addClass("btn").attr("data-action", "incrementMinutes").append(a("<span>").addClass(d.icons.up)))), c.append(a("<td>").append(a("<span>").addClass("timepicker-minute").attr({
                    "data-time-component": "minutes",
                    title: d.tooltips.pickMinute
                }).attr("data-action", "showMinutes"))), e.append(a("<td>").append(a("<a>").attr({
                    href: "#",
                    tabindex: "-1",
                    title: d.tooltips.decrementMinute
                }).addClass("btn").attr("data-action", "decrementMinutes").append(a("<span>").addClass(d.icons.down))))), y("s") && (y("m") && (b.append(a("<td>").addClass("separator")), c.append(a("<td>").addClass("separator").html(":")), e.append(a("<td>").addClass("separator"))), b.append(a("<td>").append(a("<a>").attr({
                    href: "#",
                    tabindex: "-1",
                    title: d.tooltips.incrementSecond
                }).addClass("btn").attr("data-action", "incrementSeconds").append(a("<span>").addClass(d.icons.up)))), c.append(a("<td>").append(a("<span>").addClass("timepicker-second").attr({
                    "data-time-component": "seconds",
                    title: d.tooltips.pickSecond
                }).attr("data-action", "showSeconds"))), e.append(a("<td>").append(a("<a>").attr({
                    href: "#",
                    tabindex: "-1",
                    title: d.tooltips.decrementSecond
                }).addClass("btn").attr("data-action", "decrementSeconds").append(a("<span>").addClass(d.icons.down))))), h || (b.append(a("<td>").addClass("separator")), c.append(a("<td>").append(a("<button>").addClass("btn btn-primary").attr({
                    "data-action": "togglePeriod",
                    tabindex: "-1",
                    title: d.tooltips.togglePeriod
                }))), e.append(a("<td>").addClass("separator"))), a("<div>").addClass("timepicker-picker").append(a("<table>").addClass("table-condensed").append([b, c, e]))
            },
            D = function() {
                var b = a("<div>").addClass("timepicker-hours").append(a("<table>").addClass("table-condensed")),
                    c = a("<div>").addClass("timepicker-minutes").append(a("<table>").addClass("table-condensed")),
                    d = a("<div>").addClass("timepicker-seconds").append(a("<table>").addClass("table-condensed")),
                    e = [C()];
                return y("h") && e.push(b), y("m") && e.push(c), y("s") && e.push(d), e
            },
            E = function() {
                var b = [];
                return d.showTodayButton && b.push(a("<td>").append(a("<a>").attr({
                    "data-action": "today",
                    title: d.tooltips.today
                }).append(a("<span>").addClass(d.icons.today)))), !d.sideBySide && A() && z() && b.push(a("<td>").append(a("<a>").attr({
                    "data-action": "togglePicker",
                    title: d.tooltips.selectTime
                }).append(a("<span>").addClass(d.icons.time)))), d.showClear && b.push(a("<td>").append(a("<a>").attr({
                    "data-action": "clear",
                    title: d.tooltips.clear
                }).append(a("<span>").addClass(d.icons.clear)))), d.showClose && b.push(a("<td>").append(a("<a>").attr({
                    "data-action": "close",
                    title: d.tooltips.close
                }).append(a("<span>").addClass(d.icons.close)))), a("<table>").addClass("table-condensed").append(a("<tbody>").append(a("<tr>").append(b)))
            },
            F = function() {
                var b = a("<div>").addClass("bootstrap-datetimepicker-widget dropdown-menu"),
                    c = a("<div>").addClass("datepicker").append(B()),
                    e = a("<div>").addClass("timepicker").append(D()),
                    f = a("<ul>").addClass("list-unstyled"),
                    g = a("<li>").addClass("picker-switch" + (d.collapse ? " accordion-toggle" : "")).append(E());
                return d.inline && b.removeClass("dropdown-menu"), h && b.addClass("usetwentyfour"), y("s") && !h && b.addClass("wider"), d.sideBySide && A() && z() ? (b.addClass("timepicker-sbs"), "top" === d.toolbarPlacement && b.append(g), b.append(a("<div>").addClass("row").append(c.addClass("col-md-6")).append(e.addClass("col-md-6"))), "bottom" === d.toolbarPlacement && b.append(g), b) : ("top" === d.toolbarPlacement && f.append(g), A() && f.append(a("<li>").addClass(d.collapse && z() ? "collapse in" : "").append(c)), "default" === d.toolbarPlacement && f.append(g), z() && f.append(a("<li>").addClass(d.collapse && A() ? "collapse" : "").append(e)), "bottom" === d.toolbarPlacement && f.append(g), b.append(f))
            },
            G = function() {
                var b, e = {};
                return b = c.is("input") || d.inline ? c.data() : c.find("input").data(), b.dateOptions && b.dateOptions instanceof Object && (e = a.extend(!0, e, b.dateOptions)), a.each(d, function(a) {
                    var c = "date" + a.charAt(0).toUpperCase() + a.slice(1);
                    void 0 !== b[c] && (e[a] = b[c])
                }), e
            },
            H = function() {
                var b, e = (n || c).position(),
                    f = (n || c).offset(),
                    g = d.widgetPositioning.vertical,
                    h = d.widgetPositioning.horizontal;
                if (d.widgetParent) b = d.widgetParent.append(o);
                else if (c.is("input")) b = c.after(o).parent();
                else {
                    if (d.inline) return void(b = c.append(o));
                    b = c, c.children().first().after(o)
                }
                if ("auto" === g && (g = f.top + 1.5 * o.height() >= a(window).height() + a(window).scrollTop() && o.height() + c.outerHeight() < f.top ? "top" : "bottom"), "auto" === h && (h = b.width() < f.left + o.outerWidth() / 2 && f.left + o.outerWidth() > a(window).width() ? "right" : "left"), "top" === g ? o.addClass("top").removeClass("bottom") : o.addClass("bottom").removeClass("top"), "right" === h ? o.addClass("pull-right") : o.removeClass("pull-right"), "relative" !== b.css("position") && (b = b.parents().filter(function() {
                        return "relative" === a(this).css("position")
                    }).first()), 0 === b.length) throw new Error("datetimepicker component should be placed within a relative positioned container");
                o.css({
                    top: "top" === g ? "auto" : e.top + c.outerHeight(),
                    bottom: "top" === g ? e.top + c.outerHeight() : "auto",
                    left: "left" === h ? b === c ? 0 : e.left : "auto",
                    right: "left" === h ? "auto" : b.outerWidth() - c.outerWidth() - (b === c ? 0 : e.left)
                })
            },
            I = function(a) {
                "dp.change" === a.type && (a.date && a.date.isSame(a.oldDate) || !a.date && !a.oldDate) || c.trigger(a)
            },
            J = function(a) {
                "y" === a && (a = "YYYY"), I({
                    type: "dp.update",
                    change: a,
                    viewDate: f.clone()
                })
            },
            K = function(a) {
                o && (a && (k = Math.max(p, Math.min(3, k + a))), o.find(".datepicker > div").hide().filter(".datepicker-" + q[k].clsName).show())
            },
            L = function() {
                var b = a("<tr>"),
                    c = f.clone().startOf("w").startOf("d");
                for (d.calendarWeeks === !0 && b.append(a("<th>").addClass("cw").text("#")); c.isBefore(f.clone().endOf("w"));) b.append(a("<th>").addClass("dow").text(c.format("dd"))), c.add(1, "d");
                o.find(".datepicker-days thead").append(b)
            },
            M = function(a) {
                return d.disabledDates[a.format("YYYY-MM-DD")] === !0
            },
            N = function(a) {
                return d.enabledDates[a.format("YYYY-MM-DD")] === !0
            },
            O = function(a) {
                return d.disabledHours[a.format("H")] === !0
            },
            P = function(a) {
                return d.enabledHours[a.format("H")] === !0
            },
            Q = function(b, c) {
                if (!b.isValid()) return !1;
                if (d.disabledDates && "d" === c && M(b)) return !1;
                if (d.enabledDates && "d" === c && !N(b)) return !1;
                if (d.minDate && b.isBefore(d.minDate, c)) return !1;
                if (d.maxDate && b.isAfter(d.maxDate, c)) return !1;
                if (d.daysOfWeekDisabled && "d" === c && -1 !== d.daysOfWeekDisabled.indexOf(b.day())) return !1;
                if (d.disabledHours && ("h" === c || "m" === c || "s" === c) && O(b)) return !1;
                if (d.enabledHours && ("h" === c || "m" === c || "s" === c) && !P(b)) return !1;
                if (d.disabledTimeIntervals && ("h" === c || "m" === c || "s" === c)) {
                    var e = !1;
                    if (a.each(d.disabledTimeIntervals, function() {
                            return b.isBetween(this[0], this[1]) ? (e = !0, !1) : void 0
                        }), e) return !1
                }
                return !0
            },
            R = function() {
                for (var b = [], c = f.clone().startOf("y").startOf("d"); c.isSame(f, "y");) b.push(a("<span>").attr("data-action", "selectMonth").addClass("month").text(c.format("MMM"))), c.add(1, "M");
                o.find(".datepicker-months td").empty().append(b)
            },
            S = function() {
                var b = o.find(".datepicker-months"),
                    c = b.find("th"),
                    g = b.find("tbody").find("span");
                c.eq(0).find("span").attr("title", d.tooltips.prevYear), c.eq(1).attr("title", d.tooltips.selectYear), c.eq(2).find("span").attr("title", d.tooltips.nextYear), b.find(".disabled").removeClass("disabled"), Q(f.clone().subtract(1, "y"), "y") || c.eq(0).addClass("disabled"), c.eq(1).text(f.year()), Q(f.clone().add(1, "y"), "y") || c.eq(2).addClass("disabled"), g.removeClass("active"), e.isSame(f, "y") && !m && g.eq(e.month()).addClass("active"), g.each(function(b) {
                    Q(f.clone().month(b), "M") || a(this).addClass("disabled")
                })
            },
            T = function() {
                var a = o.find(".datepicker-years"),
                    b = a.find("th"),
                    c = f.clone().subtract(5, "y"),
                    g = f.clone().add(6, "y"),
                    h = "";
                for (b.eq(0).find("span").attr("title", d.tooltips.prevDecade), b.eq(1).attr("title", d.tooltips.selectDecade), b.eq(2).find("span").attr("title", d.tooltips.nextDecade), a.find(".disabled").removeClass("disabled"), d.minDate && d.minDate.isAfter(c, "y") && b.eq(0).addClass("disabled"), b.eq(1).text(c.year() + "-" + g.year()), d.maxDate && d.maxDate.isBefore(g, "y") && b.eq(2).addClass("disabled"); !c.isAfter(g, "y");) h += '<span data-action="selectYear" class="year' + (c.isSame(e, "y") && !m ? " active" : "") + (Q(c, "y") ? "" : " disabled") + '">' + c.year() + "</span>", c.add(1, "y");
                a.find("td").html(h)
            },
            U = function() {
                var a = o.find(".datepicker-decades"),
                    c = a.find("th"),
                    g = b({
                        y: f.year() - f.year() % 100 - 1
                    }),
                    h = g.clone().add(100, "y"),
                    i = g.clone(),
                    j = "";
                for (c.eq(0).find("span").attr("title", d.tooltips.prevCentury), c.eq(2).find("span").attr("title", d.tooltips.nextCentury), a.find(".disabled").removeClass("disabled"), (g.isSame(b({
                        y: 1900
                    })) || d.minDate && d.minDate.isAfter(g, "y")) && c.eq(0).addClass("disabled"), c.eq(1).text(g.year() + "-" + h.year()), (g.isSame(b({
                        y: 2e3
                    })) || d.maxDate && d.maxDate.isBefore(h, "y")) && c.eq(2).addClass("disabled"); !g.isAfter(h, "y");) j += '<span data-action="selectDecade" class="decade' + (g.isSame(e, "y") ? " active" : "") + (Q(g, "y") ? "" : " disabled") + '" data-selection="' + (g.year() + 6) + '">' + (g.year() + 1) + " - " + (g.year() + 12) + "</span>", g.add(12, "y");
                j += "<span></span><span></span><span></span>", a.find("td").html(j), c.eq(1).text(i.year() + 1 + "-" + g.year())
            },
            V = function() {
                var b, c, g, h, i = o.find(".datepicker-days"),
                    j = i.find("th"),
                    k = [];
                if (A()) {
                    for (j.eq(0).find("span").attr("title", d.tooltips.prevMonth), j.eq(1).attr("title", d.tooltips.selectMonth), j.eq(2).find("span").attr("title", d.tooltips.nextMonth), i.find(".disabled").removeClass("disabled"), j.eq(1).text(f.format(d.dayViewHeaderFormat)), Q(f.clone().subtract(1, "M"), "M") || j.eq(0).addClass("disabled"), Q(f.clone().add(1, "M"), "M") || j.eq(2).addClass("disabled"), b = f.clone().startOf("M").startOf("w").startOf("d"), h = 0; 42 > h; h++) 0 === b.weekday() && (c = a("<tr>"), d.calendarWeeks && c.append('<td class="cw">' + b.week() + "</td>"), k.push(c)), g = "", b.isBefore(f, "M") && (g += " old"), b.isAfter(f, "M") && (g += " new"), b.isSame(e, "d") && !m && (g += " active"), Q(b, "d") || (g += " disabled"), b.isSame(x(), "d") && (g += " today"), (0 === b.day() || 6 === b.day()) && (g += " weekend"), c.append('<td data-action="selectDay" data-day="' + b.format("L") + '" class="day' + g + '">' + b.date() + "</td>"), b.add(1, "d");
                    i.find("tbody").empty().append(k), S(), T(), U()
                }
            },
            W = function() {
                var b = o.find(".timepicker-hours table"),
                    c = f.clone().startOf("d"),
                    d = [],
                    e = a("<tr>");
                for (f.hour() > 11 && !h && c.hour(12); c.isSame(f, "d") && (h || f.hour() < 12 && c.hour() < 12 || f.hour() > 11);) c.hour() % 4 === 0 && (e = a("<tr>"), d.push(e)), e.append('<td data-action="selectHour" class="hour' + (Q(c, "h") ? "" : " disabled") + '">' + c.format(h ? "HH" : "hh") + "</td>"), c.add(1, "h");
                b.empty().append(d)
            },
            X = function() {
                for (var b = o.find(".timepicker-minutes table"), c = f.clone().startOf("h"), e = [], g = a("<tr>"), h = 1 === d.stepping ? 5 : d.stepping; f.isSame(c, "h");) c.minute() % (4 * h) === 0 && (g = a("<tr>"), e.push(g)), g.append('<td data-action="selectMinute" class="minute' + (Q(c, "m") ? "" : " disabled") + '">' + c.format("mm") + "</td>"), c.add(h, "m");
                b.empty().append(e)
            },
            Y = function() {
                for (var b = o.find(".timepicker-seconds table"), c = f.clone().startOf("m"), d = [], e = a("<tr>"); f.isSame(c, "m");) c.second() % 20 === 0 && (e = a("<tr>"), d.push(e)), e.append('<td data-action="selectSecond" class="second' + (Q(c, "s") ? "" : " disabled") + '">' + c.format("ss") + "</td>"), c.add(5, "s");
                b.empty().append(d)
            },
            Z = function() {
                var a, b, c = o.find(".timepicker span[data-time-component]");
                h || (a = o.find(".timepicker [data-action=togglePeriod]"), b = e.clone().add(e.hours() >= 12 ? -12 : 12, "h"), a.text(e.format("A")), Q(b, "h") ? a.removeClass("disabled") : a.addClass("disabled")), c.filter("[data-time-component=hours]").text(e.format(h ? "HH" : "hh")), c.filter("[data-time-component=minutes]").text(e.format("mm")), c.filter("[data-time-component=seconds]").text(e.format("ss")), W(), X(), Y()
            },
            $ = function() {
                o && (V(), Z())
            },
            _ = function(a) {
                var b = m ? null : e;
                return a ? (a = a.clone().locale(d.locale), 1 !== d.stepping && a.minutes(Math.round(a.minutes() / d.stepping) * d.stepping % 60).seconds(0), void(Q(a) ? (e = a, f = e.clone(), g.val(e.format(i)), c.data("date", e.format(i)), m = !1, $(), I({
                    type: "dp.change",
                    date: e.clone(),
                    oldDate: b
                })) : (d.keepInvalid || g.val(m ? "" : e.format(i)), I({
                    type: "dp.error",
                    date: a
                })))) : (m = !0, g.val(""), c.data("date", ""), I({
                    type: "dp.change",
                    date: !1,
                    oldDate: b
                }), void $())
            },
            aa = function() {
                var b = !1;
                return o ? (o.find(".collapse").each(function() {
                    var c = a(this).data("collapse");
                    return c && c.transitioning ? (b = !0, !1) : !0
                }), b ? l : (n && n.hasClass("btn") && n.toggleClass("active"), o.hide(), a(window).off("resize", H), o.off("click", "[data-action]"), o.off("mousedown", !1), o.remove(), o = !1, I({
                    type: "dp.hide",
                    date: e.clone()
                }), g.blur(), l)) : l
            },
            ba = function() {
                _(null)
            },
            ca = {
                next: function() {
                    var a = q[k].navFnc;
                    f.add(q[k].navStep, a), V(), J(a)
                },
                previous: function() {
                    var a = q[k].navFnc;
                    f.subtract(q[k].navStep, a), V(), J(a)
                },
                pickerSwitch: function() {
                    K(1)
                },
                selectMonth: function(b) {
                    var c = a(b.target).closest("tbody").find("span").index(a(b.target));
                    f.month(c), k === p ? (_(e.clone().year(f.year()).month(f.month())), d.inline || aa()) : (K(-1), V()), J("M")
                },
                selectYear: function(b) {
                    var c = parseInt(a(b.target).text(), 10) || 0;
                    f.year(c), k === p ? (_(e.clone().year(f.year())), d.inline || aa()) : (K(-1), V()), J("YYYY")
                },
                selectDecade: function(b) {
                    var c = parseInt(a(b.target).data("selection"), 10) || 0;
                    f.year(c), k === p ? (_(e.clone().year(f.year())), d.inline || aa()) : (K(-1), V()), J("YYYY")
                },
                selectDay: function(b) {
                    var c = f.clone();
                    a(b.target).is(".old") && c.subtract(1, "M"), a(b.target).is(".new") && c.add(1, "M"), _(c.date(parseInt(a(b.target).text(), 10))), z() || d.keepOpen || d.inline || aa()
                },
                incrementHours: function() {
                    var a = e.clone().add(1, "h");
                    Q(a, "h") && _(a)
                },
                incrementMinutes: function() {
                    var a = e.clone().add(d.stepping, "m");
                    Q(a, "m") && _(a)
                },
                incrementSeconds: function() {
                    var a = e.clone().add(1, "s");
                    Q(a, "s") && _(a)
                },
                decrementHours: function() {
                    var a = e.clone().subtract(1, "h");
                    Q(a, "h") && _(a)
                },
                decrementMinutes: function() {
                    var a = e.clone().subtract(d.stepping, "m");
                    Q(a, "m") && _(a)
                },
                decrementSeconds: function() {
                    var a = e.clone().subtract(1, "s");
                    Q(a, "s") && _(a)
                },
                togglePeriod: function() {
                    _(e.clone().add(e.hours() >= 12 ? -12 : 12, "h"))
                },
                togglePicker: function(b) {
                    var c, e = a(b.target),
                        f = e.closest("ul"),
                        g = f.find(".in"),
                        h = f.find(".collapse:not(.in)");
                    if (g && g.length) {
                        if (c = g.data("collapse"), c && c.transitioning) return;
                        g.collapse ? (g.collapse("hide"), h.collapse("show")) : (g.removeClass("in"), h.addClass("in")), e.is("span") ? e.toggleClass(d.icons.time + " " + d.icons.date) : e.find("span").toggleClass(d.icons.time + " " + d.icons.date)
                    }
                },
                showPicker: function() {
                    o.find(".timepicker > div:not(.timepicker-picker)").hide(), o.find(".timepicker .timepicker-picker").show()
                },
                showHours: function() {
                    o.find(".timepicker .timepicker-picker").hide(), o.find(".timepicker .timepicker-hours").show()
                },
                showMinutes: function() {
                    o.find(".timepicker .timepicker-picker").hide(), o.find(".timepicker .timepicker-minutes").show()
                },
                showSeconds: function() {
                    o.find(".timepicker .timepicker-picker").hide(), o.find(".timepicker .timepicker-seconds").show()
                },
                selectHour: function(b) {
                    var c = parseInt(a(b.target).text(), 10);
                    h || (e.hours() >= 12 ? 12 !== c && (c += 12) : 12 === c && (c = 0)), _(e.clone().hours(c)), ca.showPicker.call(l)
                },
                selectMinute: function(b) {
                    _(e.clone().minutes(parseInt(a(b.target).text(), 10))), ca.showPicker.call(l)
                },
                selectSecond: function(b) {
                    _(e.clone().seconds(parseInt(a(b.target).text(), 10))), ca.showPicker.call(l)
                },
                clear: ba,
                today: function() {
                    var a = x();
                    Q(a, "d") && _(a)
                },
                close: aa
            },
            da = function(b) {
                return a(b.currentTarget).is(".disabled") ? !1 : (ca[a(b.currentTarget).data("action")].apply(l, arguments), !1)
            },
            ea = function() {
                var b, c = {
                    year: function(a) {
                        return a.month(0).date(1).hours(0).seconds(0).minutes(0)
                    },
                    month: function(a) {
                        return a.date(1).hours(0).seconds(0).minutes(0)
                    },
                    day: function(a) {
                        return a.hours(0).seconds(0).minutes(0)
                    },
                    hour: function(a) {
                        return a.seconds(0).minutes(0)
                    },
                    minute: function(a) {
                        return a.seconds(0)
                    }
                };
                return g.prop("disabled") || !d.ignoreReadonly && g.prop("readonly") || o ? l : (void 0 !== g.val() && 0 !== g.val().trim().length ? _(ga(g.val().trim())) : d.useCurrent && m && (g.is("input") && 0 === g.val().trim().length || d.inline) && (b = x(), "string" == typeof d.useCurrent && (b = c[d.useCurrent](b)), _(b)), o = F(), L(), R(), o.find(".timepicker-hours").hide(), o.find(".timepicker-minutes").hide(), o.find(".timepicker-seconds").hide(), $(), K(), a(window).on("resize", H), o.on("click", "[data-action]", da), o.on("mousedown", !1), n && n.hasClass("btn") && n.toggleClass("active"), o.show(), H(), d.focusOnShow && !g.is(":focus") && g.focus(), I({
                    type: "dp.show"
                }), l)
            },
            fa = function() {
                return o ? aa() : ea()
            },
            ga = function(a) {
                return a = void 0 === d.parseInputDate ? b.isMoment(a) || a instanceof Date ? b(a) : x(a) : d.parseInputDate(a), a.locale(d.locale), a
            },
            ha = function(a) {
                var b, c, e, f, g = null,
                    h = [],
                    i = {},
                    j = a.which,
                    k = "p";
                w[j] = k;
                for (b in w) w.hasOwnProperty(b) && w[b] === k && (h.push(b), parseInt(b, 10) !== j && (i[b] = !0));
                for (b in d.keyBinds)
                    if (d.keyBinds.hasOwnProperty(b) && "function" == typeof d.keyBinds[b] && (e = b.split(" "), e.length === h.length && v[j] === e[e.length - 1])) {
                        for (f = !0, c = e.length - 2; c >= 0; c--)
                            if (!(v[e[c]] in i)) {
                                f = !1;
                                break
                            } if (f) {
                            g = d.keyBinds[b];
                            break
                        }
                    } g && (g.call(l, o), a.stopPropagation(), a.preventDefault())
            },
            ia = function(a) {
                w[a.which] = "r", a.stopPropagation(), a.preventDefault()
            },
            ja = function(b) {
                var c = a(b.target).val().trim(),
                    d = c ? ga(c) : null;
                return _(d), b.stopImmediatePropagation(), !1
            },
            ka = function() {
                g.on({
                    change: ja,
                    blur: d.debug ? "" : aa,
                    keydown: ha,
                    keyup: ia,
                    focus: d.allowInputToggle ? ea : ""
                }), c.is("input") ? g.on({
                    focus: ea
                }) : n && (n.on("click", fa), n.on("mousedown", !1))
            },
            la = function() {
                g.off({
                    change: ja,
                    blur: blur,
                    keydown: ha,
                    keyup: ia,
                    focus: d.allowInputToggle ? aa : ""
                }), c.is("input") ? g.off({
                    focus: ea
                }) : n && (n.off("click", fa), n.off("mousedown", !1))
            },
            ma = function(b) {
                var c = {};
                return a.each(b, function() {
                    var a = ga(this);
                    a.isValid() && (c[a.format("YYYY-MM-DD")] = !0)
                }), Object.keys(c).length ? c : !1
            },
            na = function(b) {
                var c = {};
                return a.each(b, function() {
                    c[this] = !0
                }), Object.keys(c).length ? c : !1
            },
            oa = function() {
                var a = d.format || "L LT";
                i = a.replace(/(\[[^\[]*\])|(\\)?(LTS|LT|LL?L?L?|l{1,4})/g, function(a) {
                    var b = e.localeData().longDateFormat(a) || a;
                    return b.replace(/(\[[^\[]*\])|(\\)?(LTS|LT|LL?L?L?|l{1,4})/g, function(a) {
                        return e.localeData().longDateFormat(a) || a
                    })
                }), j = d.extraFormats ? d.extraFormats.slice() : [], j.indexOf(a) < 0 && j.indexOf(i) < 0 && j.push(i), h = i.toLowerCase().indexOf("a") < 1 && i.replace(/\[.*?\]/g, "").indexOf("h") < 1, y("y") && (p = 2), y("M") && (p = 1), y("d") && (p = 0), k = Math.max(p, k), m || _(e)
            };
        if (l.destroy = function() {
                aa(), la(), c.removeData("DateTimePicker"), c.removeData("date")
            }, l.toggle = fa, l.show = ea, l.hide = aa, l.disable = function() {
                return aa(), n && n.hasClass("btn") && n.addClass("disabled"), g.prop("disabled", !0), l
            }, l.enable = function() {
                return n && n.hasClass("btn") && n.removeClass("disabled"), g.prop("disabled", !1), l
            }, l.ignoreReadonly = function(a) {
                if (0 === arguments.length) return d.ignoreReadonly;
                if ("boolean" != typeof a) throw new TypeError("ignoreReadonly () expects a boolean parameter");
                return d.ignoreReadonly = a, l
            }, l.options = function(b) {
                if (0 === arguments.length) return a.extend(!0, {}, d);
                if (!(b instanceof Object)) throw new TypeError("options() options parameter should be an object");
                return a.extend(!0, d, b), a.each(d, function(a, b) {
                    if (void 0 === l[a]) throw new TypeError("option " + a + " is not recognized!");
                    l[a](b)
                }), l
            }, l.date = function(a) {
                if (0 === arguments.length) return m ? null : e.clone();
                if (!(null === a || "string" == typeof a || b.isMoment(a) || a instanceof Date)) throw new TypeError("date() parameter must be one of [null, string, moment or Date]");
                return _(null === a ? null : ga(a)), l
            }, l.format = function(a) {
                if (0 === arguments.length) return d.format;
                if ("string" != typeof a && ("boolean" != typeof a || a !== !1)) throw new TypeError("format() expects a sting or boolean:false parameter " + a);
                return d.format = a, i && oa(), l
            }, l.timeZone = function(a) {
                return 0 === arguments.length ? d.timeZone : (d.timeZone = a, l)
            }, l.dayViewHeaderFormat = function(a) {
                if (0 === arguments.length) return d.dayViewHeaderFormat;
                if ("string" != typeof a) throw new TypeError("dayViewHeaderFormat() expects a string parameter");
                return d.dayViewHeaderFormat = a, l
            }, l.extraFormats = function(a) {
                if (0 === arguments.length) return d.extraFormats;
                if (a !== !1 && !(a instanceof Array)) throw new TypeError("extraFormats() expects an array or false parameter");
                return d.extraFormats = a, j && oa(), l
            }, l.disabledDates = function(b) {
                if (0 === arguments.length) return d.disabledDates ? a.extend({}, d.disabledDates) : d.disabledDates;
                if (!b) return d.disabledDates = !1, $(), l;
                if (!(b instanceof Array)) throw new TypeError("disabledDates() expects an array parameter");
                return d.disabledDates = ma(b), d.enabledDates = !1, $(), l
            }, l.enabledDates = function(b) {
                if (0 === arguments.length) return d.enabledDates ? a.extend({}, d.enabledDates) : d.enabledDates;
                if (!b) return d.enabledDates = !1, $(), l;
                if (!(b instanceof Array)) throw new TypeError("enabledDates() expects an array parameter");
                return d.enabledDates = ma(b), d.disabledDates = !1, $(), l
            }, l.daysOfWeekDisabled = function(a) {
                if (0 === arguments.length) return d.daysOfWeekDisabled.splice(0);
                if ("boolean" == typeof a && !a) return d.daysOfWeekDisabled = !1, $(), l;
                if (!(a instanceof Array)) throw new TypeError("daysOfWeekDisabled() expects an array parameter");
                if (d.daysOfWeekDisabled = a.reduce(function(a, b) {
                        return b = parseInt(b, 10), b > 6 || 0 > b || isNaN(b) ? a : (-1 === a.indexOf(b) && a.push(b), a)
                    }, []).sort(), d.useCurrent && !d.keepInvalid) {
                    for (var b = 0; !Q(e, "d");) {
                        if (e.add(1, "d"), 7 === b) throw "Tried 7 times to find a valid date";
                        b++
                    }
                    _(e)
                }
                return $(), l
            }, l.maxDate = function(a) {
                if (0 === arguments.length) return d.maxDate ? d.maxDate.clone() : d.maxDate;
                if ("boolean" == typeof a && a === !1) return d.maxDate = !1, $(), l;
                "string" == typeof a && ("now" === a || "moment" === a) && (a = x());
                var b = ga(a);
                if (!b.isValid()) throw new TypeError("maxDate() Could not parse date parameter: " + a);
                if (d.minDate && b.isBefore(d.minDate)) throw new TypeError("maxDate() date parameter is before options.minDate: " + b.format(i));
                return d.maxDate = b, d.useCurrent && !d.keepInvalid && e.isAfter(a) && _(d.maxDate), f.isAfter(b) && (f = b.clone().subtract(d.stepping, "m")), $(), l
            }, l.minDate = function(a) {
                if (0 === arguments.length) return d.minDate ? d.minDate.clone() : d.minDate;
                if ("boolean" == typeof a && a === !1) return d.minDate = !1, $(), l;
                "string" == typeof a && ("now" === a || "moment" === a) && (a = x());
                var b = ga(a);
                if (!b.isValid()) throw new TypeError("minDate() Could not parse date parameter: " + a);
                if (d.maxDate && b.isAfter(d.maxDate)) throw new TypeError("minDate() date parameter is after options.maxDate: " + b.format(i));
                return d.minDate = b, d.useCurrent && !d.keepInvalid && e.isBefore(a) && _(d.minDate), f.isBefore(b) && (f = b.clone().add(d.stepping, "m")), $(), l
            }, l.defaultDate = function(a) {
                if (0 === arguments.length) return d.defaultDate ? d.defaultDate.clone() : d.defaultDate;
                if (!a) return d.defaultDate = !1, l;
                "string" == typeof a && ("now" === a || "moment" === a) && (a = x());
                var b = ga(a);
                if (!b.isValid()) throw new TypeError("defaultDate() Could not parse date parameter: " + a);
                if (!Q(b)) throw new TypeError("defaultDate() date passed is invalid according to component setup validations");
                return d.defaultDate = b, (d.defaultDate && d.inline || "" === g.val().trim()) && _(d.defaultDate), l
            }, l.locale = function(a) {
                if (0 === arguments.length) return d.locale;
                if (!b.localeData(a)) throw new TypeError("locale() locale " + a + " is not loaded from moment locales!");
                return d.locale = a, e.locale(d.locale), f.locale(d.locale), i && oa(), o && (aa(), ea()), l
            }, l.stepping = function(a) {
                return 0 === arguments.length ? d.stepping : (a = parseInt(a, 10), (isNaN(a) || 1 > a) && (a = 1), d.stepping = a, l)
            }, l.useCurrent = function(a) {
                var b = ["year", "month", "day", "hour", "minute"];
                if (0 === arguments.length) return d.useCurrent;
                if ("boolean" != typeof a && "string" != typeof a) throw new TypeError("useCurrent() expects a boolean or string parameter");
                if ("string" == typeof a && -1 === b.indexOf(a.toLowerCase())) throw new TypeError("useCurrent() expects a string parameter of " + b.join(", "));
                return d.useCurrent = a, l
            }, l.collapse = function(a) {
                if (0 === arguments.length) return d.collapse;
                if ("boolean" != typeof a) throw new TypeError("collapse() expects a boolean parameter");
                return d.collapse === a ? l : (d.collapse = a, o && (aa(), ea()), l)
            }, l.icons = function(b) {
                if (0 === arguments.length) return a.extend({}, d.icons);
                if (!(b instanceof Object)) throw new TypeError("icons() expects parameter to be an Object");
                return a.extend(d.icons, b), o && (aa(), ea()), l
            }, l.tooltips = function(b) {
                if (0 === arguments.length) return a.extend({}, d.tooltips);
                if (!(b instanceof Object)) throw new TypeError("tooltips() expects parameter to be an Object");
                return a.extend(d.tooltips, b), o && (aa(), ea()), l
            }, l.useStrict = function(a) {
                if (0 === arguments.length) return d.useStrict;
                if ("boolean" != typeof a) throw new TypeError("useStrict() expects a boolean parameter");
                return d.useStrict = a, l
            }, l.sideBySide = function(a) {
                if (0 === arguments.length) return d.sideBySide;
                if ("boolean" != typeof a) throw new TypeError("sideBySide() expects a boolean parameter");
                return d.sideBySide = a, o && (aa(), ea()), l
            }, l.viewMode = function(a) {
                if (0 === arguments.length) return d.viewMode;
                if ("string" != typeof a) throw new TypeError("viewMode() expects a string parameter");
                if (-1 === r.indexOf(a)) throw new TypeError("viewMode() parameter must be one of (" + r.join(", ") + ") value");
                return d.viewMode = a, k = Math.max(r.indexOf(a), p), K(), l
            }, l.toolbarPlacement = function(a) {
                if (0 === arguments.length) return d.toolbarPlacement;
                if ("string" != typeof a) throw new TypeError("toolbarPlacement() expects a string parameter");
                if (-1 === u.indexOf(a)) throw new TypeError("toolbarPlacement() parameter must be one of (" + u.join(", ") + ") value");
                return d.toolbarPlacement = a, o && (aa(), ea()), l
            }, l.widgetPositioning = function(b) {
                if (0 === arguments.length) return a.extend({}, d.widgetPositioning);
                if ("[object Object]" !== {}.toString.call(b)) throw new TypeError("widgetPositioning() expects an object variable");
                if (b.horizontal) {
                    if ("string" != typeof b.horizontal) throw new TypeError("widgetPositioning() horizontal variable must be a string");
                    if (b.horizontal = b.horizontal.toLowerCase(), -1 === t.indexOf(b.horizontal)) throw new TypeError("widgetPositioning() expects horizontal parameter to be one of (" + t.join(", ") + ")");
                    d.widgetPositioning.horizontal = b.horizontal
                }
                if (b.vertical) {
                    if ("string" != typeof b.vertical) throw new TypeError("widgetPositioning() vertical variable must be a string");
                    if (b.vertical = b.vertical.toLowerCase(), -1 === s.indexOf(b.vertical)) throw new TypeError("widgetPositioning() expects vertical parameter to be one of (" + s.join(", ") + ")");
                    d.widgetPositioning.vertical = b.vertical
                }
                return $(), l
            }, l.calendarWeeks = function(a) {
                if (0 === arguments.length) return d.calendarWeeks;
                if ("boolean" != typeof a) throw new TypeError("calendarWeeks() expects parameter to be a boolean value");
                return d.calendarWeeks = a, $(), l
            }, l.showTodayButton = function(a) {
                if (0 === arguments.length) return d.showTodayButton;
                if ("boolean" != typeof a) throw new TypeError("showTodayButton() expects a boolean parameter");
                return d.showTodayButton = a, o && (aa(), ea()), l
            }, l.showClear = function(a) {
                if (0 === arguments.length) return d.showClear;
                if ("boolean" != typeof a) throw new TypeError("showClear() expects a boolean parameter");
                return d.showClear = a, o && (aa(), ea()), l
            }, l.widgetParent = function(b) {
                if (0 === arguments.length) return d.widgetParent;
                if ("string" == typeof b && (b = a(b)), null !== b && "string" != typeof b && !(b instanceof a)) throw new TypeError("widgetParent() expects a string or a jQuery object parameter");
                return d.widgetParent = b, o && (aa(), ea()), l
            }, l.keepOpen = function(a) {
                if (0 === arguments.length) return d.keepOpen;
                if ("boolean" != typeof a) throw new TypeError("keepOpen() expects a boolean parameter");
                return d.keepOpen = a, l
            }, l.focusOnShow = function(a) {
                if (0 === arguments.length) return d.focusOnShow;
                if ("boolean" != typeof a) throw new TypeError("focusOnShow() expects a boolean parameter");
                return d.focusOnShow = a, l
            }, l.inline = function(a) {
                if (0 === arguments.length) return d.inline;
                if ("boolean" != typeof a) throw new TypeError("inline() expects a boolean parameter");
                return d.inline = a, l
            }, l.clear = function() {
                return ba(), l
            }, l.keyBinds = function(a) {
                return d.keyBinds = a, l
            }, l.getMoment = function(a) {
                return x(a)
            }, l.debug = function(a) {
                if ("boolean" != typeof a) throw new TypeError("debug() expects a boolean parameter");
                return d.debug = a, l
            }, l.allowInputToggle = function(a) {
                if (0 === arguments.length) return d.allowInputToggle;
                if ("boolean" != typeof a) throw new TypeError("allowInputToggle() expects a boolean parameter");
                return d.allowInputToggle = a, l
            }, l.showClose = function(a) {
                if (0 === arguments.length) return d.showClose;
                if ("boolean" != typeof a) throw new TypeError("showClose() expects a boolean parameter");
                return d.showClose = a, l
            }, l.keepInvalid = function(a) {
                if (0 === arguments.length) return d.keepInvalid;
                if ("boolean" != typeof a) throw new TypeError("keepInvalid() expects a boolean parameter");
                return d.keepInvalid = a, l
            }, l.datepickerInput = function(a) {
                if (0 === arguments.length) return d.datepickerInput;
                if ("string" != typeof a) throw new TypeError("datepickerInput() expects a string parameter");
                return d.datepickerInput = a, l
            }, l.parseInputDate = function(a) {
                if (0 === arguments.length) return d.parseInputDate;
                if ("function" != typeof a) throw new TypeError("parseInputDate() sholud be as function");
                return d.parseInputDate = a, l
            }, l.disabledTimeIntervals = function(b) {
                if (0 === arguments.length) return d.disabledTimeIntervals ? a.extend({}, d.disabledTimeIntervals) : d.disabledTimeIntervals;
                if (!b) return d.disabledTimeIntervals = !1, $(), l;
                if (!(b instanceof Array)) throw new TypeError("disabledTimeIntervals() expects an array parameter");
                return d.disabledTimeIntervals = b, $(), l
            }, l.disabledHours = function(b) {
                if (0 === arguments.length) return d.disabledHours ? a.extend({}, d.disabledHours) : d.disabledHours;
                if (!b) return d.disabledHours = !1, $(), l;
                if (!(b instanceof Array)) throw new TypeError("disabledHours() expects an array parameter");
                if (d.disabledHours = na(b), d.enabledHours = !1, d.useCurrent && !d.keepInvalid) {
                    for (var c = 0; !Q(e, "h");) {
                        if (e.add(1, "h"), 24 === c) throw "Tried 24 times to find a valid date";
                        c++
                    }
                    _(e)
                }
                return $(), l
            }, l.enabledHours = function(b) {
                if (0 === arguments.length) return d.enabledHours ? a.extend({}, d.enabledHours) : d.enabledHours;
                if (!b) return d.enabledHours = !1, $(), l;
                if (!(b instanceof Array)) throw new TypeError("enabledHours() expects an array parameter");
                if (d.enabledHours = na(b), d.disabledHours = !1, d.useCurrent && !d.keepInvalid) {
                    for (var c = 0; !Q(e, "h");) {
                        if (e.add(1, "h"), 24 === c) throw "Tried 24 times to find a valid date";
                        c++
                    }
                    _(e)
                }
                return $(), l
            }, l.viewDate = function(a) {
                if (0 === arguments.length) return f.clone();
                if (!a) return f = e.clone(), l;
                if (!("string" == typeof a || b.isMoment(a) || a instanceof Date)) throw new TypeError("viewDate() parameter must be one of [string, moment or Date]");
                return f = ga(a), J(), l
            }, c.is("input")) g = c;
        else if (g = c.find(d.datepickerInput), 0 === g.size()) g = c.find("input");
        else if (!g.is("input")) throw new Error('CSS class "' + d.datepickerInput + '" cannot be applied to non input element');
        if (c.hasClass("input-group") && (n = 0 === c.find(".datepickerbutton").size() ? c.find(".input-group-addon") : c.find(".datepickerbutton")), !d.inline && !g.is("input")) throw new Error("Could not initialize DateTimePicker without an input element");
        return e = x(), f = e.clone(), a.extend(!0, d, G()), l.options(d), oa(), ka(), g.prop("disabled") && l.disable(), g.is("input") && 0 !== g.val().trim().length ? _(ga(g.val().trim())) : d.defaultDate && void 0 === g.attr("placeholder") && _(d.defaultDate), d.inline && ea(), l
    };
    a.fn.datetimepicker = function(b) {
        return this.each(function() {
            var d = a(this);
            d.data("DateTimePicker") || (b = a.extend(!0, {}, a.fn.datetimepicker.defaults, b), d.data("DateTimePicker", c(d, b)))
        })
    }, a.fn.datetimepicker.defaults = {
        timeZone: "Etc/UTC",
        format: !1,
        dayViewHeaderFormat: "MMMM YYYY",
        extraFormats: !1,
        stepping: 1,
        minDate: !1,
        maxDate: !1,
        useCurrent: !0,
        collapse: !0,
        locale: b.locale(),
        defaultDate: !1,
        disabledDates: !1,
        enabledDates: !1,
        icons: {
            time: "glyphicon glyphicon-time",
            date: "glyphicon glyphicon-calendar",
            up: "glyphicon glyphicon-chevron-up",
            down: "glyphicon glyphicon-chevron-down",
            previous: "glyphicon glyphicon-chevron-left",
            next: "glyphicon glyphicon-chevron-right",
            today: "glyphicon glyphicon-screenshot",
            clear: "glyphicon glyphicon-trash",
            close: "glyphicon glyphicon-remove"
        },
        tooltips: {
            today: "Go to today",
            clear: "Clear selection",
            close: "Close the picker",
            selectMonth: "Select Month",
            prevMonth: "Previous Month",
            nextMonth: "Next Month",
            selectYear: "Select Year",
            prevYear: "Previous Year",
            nextYear: "Next Year",
            selectDecade: "Select Decade",
            prevDecade: "Previous Decade",
            nextDecade: "Next Decade",
            prevCentury: "Previous Century",
            nextCentury: "Next Century",
            pickHour: "Pick Hour",
            incrementHour: "Increment Hour",
            decrementHour: "Decrement Hour",
            pickMinute: "Pick Minute",
            incrementMinute: "Increment Minute",
            decrementMinute: "Decrement Minute",
            pickSecond: "Pick Second",
            incrementSecond: "Increment Second",
            decrementSecond: "Decrement Second",
            togglePeriod: "Toggle Period",
            selectTime: "Select Time"
        },
        useStrict: !1,
        sideBySide: !1,
        daysOfWeekDisabled: !1,
        calendarWeeks: !1,
        viewMode: "days",
        toolbarPlacement: "default",
        showTodayButton: !1,
        showClear: !1,
        showClose: !1,
        widgetPositioning: {
            horizontal: "auto",
            vertical: "auto"
        },
        widgetParent: null,
        ignoreReadonly: !1,
        keepOpen: !1,
        focusOnShow: !0,
        inline: !1,
        keepInvalid: !1,
        datepickerInput: ".datepickerinput",
        keyBinds: {
            up: function(a) {
                if (a) {
                    var b = this.date() || this.getMoment();
                    a.find(".datepicker").is(":visible") ? this.date(b.clone().subtract(7, "d")) : this.date(b.clone().add(this.stepping(), "m"))
                }
            },
            down: function(a) {
                if (!a) return void this.show();
                var b = this.date() || this.getMoment();
                a.find(".datepicker").is(":visible") ? this.date(b.clone().add(7, "d")) : this.date(b.clone().subtract(this.stepping(), "m"))
            },
            "control up": function(a) {
                if (a) {
                    var b = this.date() || this.getMoment();
                    a.find(".datepicker").is(":visible") ? this.date(b.clone().subtract(1, "y")) : this.date(b.clone().add(1, "h"))
                }
            },
            "control down": function(a) {
                if (a) {
                    var b = this.date() || this.getMoment();
                    a.find(".datepicker").is(":visible") ? this.date(b.clone().add(1, "y")) : this.date(b.clone().subtract(1, "h"))
                }
            },
            left: function(a) {
                if (a) {
                    var b = this.date() || this.getMoment();
                    a.find(".datepicker").is(":visible") && this.date(b.clone().subtract(1, "d"))
                }
            },
            right: function(a) {
                if (a) {
                    var b = this.date() || this.getMoment();
                    a.find(".datepicker").is(":visible") && this.date(b.clone().add(1, "d"))
                }
            },
            pageUp: function(a) {
                if (a) {
                    var b = this.date() || this.getMoment();
                    a.find(".datepicker").is(":visible") && this.date(b.clone().subtract(1, "M"))
                }
            },
            pageDown: function(a) {
                if (a) {
                    var b = this.date() || this.getMoment();
                    a.find(".datepicker").is(":visible") && this.date(b.clone().add(1, "M"))
                }
            },
            enter: function() {
                this.hide()
            },
            escape: function() {
                this.hide()
            },
            "control space": function(a) {
                a.find(".timepicker").is(":visible") && a.find('.btn[data-action="togglePeriod"]').click()
            },
            t: function() {
                this.date(this.getMoment())
            },
            "delete": function() {
                this.clear()
            }
        },
        debug: !1,
        allowInputToggle: !1,
        disabledTimeIntervals: !1,
        disabledHours: !1,
        enabledHours: !1,
        viewDate: !1
    }
});