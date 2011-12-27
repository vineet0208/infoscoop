/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */


gadgets.i18n = gadgets.i18n || {};

gadgets.i18n.DateTimeConstants = {
  ERAS: ['\u0f66\u0fa4\u0fb1\u0f72\u0f0b\u0f63\u0f7c\u0f0b\u0f66\u0f94\u0f7c\u0f53\u0f0d', '\u0f66\u0fa4\u0fb1\u0f72\u0f0b\u0f63\u0f7c\u0f0d'],
  ERANAMES: ['\u0f66\u0fa4\u0fb1\u0f72\u0f0b\u0f63\u0f7c\u0f0b\u0f66\u0f94\u0f7c\u0f53\u0f0d', '\u0f66\u0fa4\u0fb1\u0f72\u0f0b\u0f63\u0f7c\u0f0d'],
  NARROWMONTHS: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
  MONTHS: ['\u0f5f\u0fb3\u0f0b\u0f56\u0f0b\u0f51\u0f44\u0f0b\u0f54\u0f7c\u0f0b', '\u0f5f\u0fb3\u0f0b\u0f56\u0f0b\u0f42\u0f49\u0f72\u0f66\u0f0b\u0f54\u0f0b', '\u0f5f\u0fb3\u0f0b\u0f56\u0f0b\u0f66\u0f74\u0f58\u0f0b\u0f54\u0f0b', '\u0f5f\u0fb3\u0f0b\u0f56\u0f0b\u0f56\u0f5e\u0f72\u0f0b\u0f54\u0f0b', '\u0f5f\u0fb3\u0f0b\u0f56\u0f0b\u0f63\u0f94\u0f0b\u0f54\u0f0b', '\u0f5f\u0fb3\u0f0b\u0f56\u0f0b\u0f51\u0fb2\u0f74\u0f42\u0f0b\u0f54\u0f0b', '\u0f5f\u0fb3\u0f0b\u0f56\u0f0b\u0f56\u0f51\u0f74\u0f53\u0f0b\u0f54\u0f0b', '\u0f5f\u0fb3\u0f0b\u0f56\u0f0b\u0f56\u0f62\u0f92\u0fb1\u0f51\u0f0b\u0f54\u0f0b', '\u0f5f\u0fb3\u0f0b\u0f56\u0f0b\u0f51\u0f42\u0f74\u0f0b\u0f54\u0f0b', '\u0f5f\u0fb3\u0f0b\u0f56\u0f0b\u0f56\u0f45\u0f74\u0f0b\u0f54\u0f0b', '\u0f5f\u0fb3\u0f0b\u0f56\u0f0b\u0f56\u0f45\u0f74\u0f0b\u0f42\u0f45\u0f72\u0f42\u0f0b\u0f54\u0f0b', '\u0f5f\u0fb3\u0f0b\u0f56\u0f0b\u0f56\u0f45\u0f74\u0f0b\u0f42\u0f49\u0f72\u0f66\u0f0b\u0f54\u0f0b'],
  SHORTMONTHS: ['\u0f5f\u0fb3\u0f0b\u0f21', '\u0f5f\u0fb3\u0f0b\u0f22', '\u0f5f\u0fb3\u0f0b\u0f23', '\u0f5f\u0fb3\u0f0b\u0f24', '\u0f5f\u0fb3\u0f0b\u0f25', '\u0f5f\u0fb3\u0f0b\u0f26', '\u0f5f\u0fb3\u0f0b\u0f27', '\u0f5f\u0fb3\u0f0b\u0f28', '\u0f5f\u0fb3\u0f0b\u0f29', '\u0f5f\u0fb3\u0f0b\u0f21\u0f20', '\u0f5f\u0fb3\u0f0b\u0f21\u0f21', '\u0f5f\u0fb3\u0f0b\u0f21\u0f22'],
  WEEKDAYS: ['\u0f42\u0f5f\u0f60\u0f0b\u0f49\u0f72\u0f0b\u0f58\u0f0b', '\u0f42\u0f5f\u0f60\u0f0b\u0f5f\u0fb3\u0f0b\u0f56\u0f0b', '\u0f42\u0f5f\u0f60\u0f0b\u0f58\u0f72\u0f42\u0f0b\u0f51\u0f58\u0f62\u0f0b', '\u0f42\u0f5f\u0f60\u0f0b\u0f67\u0fb3\u0f42\u0f0b\u0f54\u0f0b', '\u0f42\u0f5f\u0f60\u0f0b\u0f55\u0f74\u0f62\u0f0b\u0f56\u0f74\u0f0b', '\u0f42\u0f5f\u0f60\u0f0b\u0f66\u0f44\u0f66\u0f0b', '\u0f42\u0f5f\u0f60\u0f0b\u0f66\u0fa4\u0f7a\u0f53\u0f0b\u0f54\u0f0b'],
  SHORTWEEKDAYS: ['\u0f49\u0f72\u0f0b\u0f58\u0f0b', '\u0f5f\u0fb3\u0f0b\u0f56\u0f0b', '\u0f58\u0f72\u0f42\u0f0b\u0f51\u0f58\u0f62\u0f0b', '\u0f67\u0fb3\u0f42\u0f0b\u0f54\u0f0b', '\u0f55\u0f74\u0f62\u0f0b\u0f56\u0f74\u0f0b', '\u0f66\u0f44\u0f66\u0f0b', '\u0f66\u0fa4\u0f7a\u0f53\u0f0b\u0f54\u0f0b'],
  NARROWWEEKDAYS: ['\u0f49\u0f72', '\u0f5f\u0fb3', '\u0f58\u0f72', '\u0f67\u0fb3', '\u0f55\u0f74', '\u0f66', '\u0f66\u0fa4\u0f7a'],
  SHORTQUARTERS: ['\u0f51\u0f74\u0f66\u0f0b\u0f5a\u0f72\u0f42\u0f66\u0f0b\u0f51\u0f44\u0f0b\u0f54\u0f7c\u0f0d', '\u0f51\u0f74\u0f66\u0f0b\u0f5a\u0f72\u0f42\u0f66\u0f0b\u0f42\u0f49\u0f72\u0f66\u0f0b\u0f54\u0f0d', '\u0f0b\u0f51\u0f74\u0f66\u0f0b\u0f5a\u0f72\u0f42\u0f66\u0f0b\u0f42\u0f66\u0f74\u0f58\u0f0b\u0f54\u0f0d', '\u0f51\u0f74\u0f66\u0f0b\u0f5a\u0f72\u0f42\u0f66\u0f0b\u0f56\u0f5e\u0f72\u0f0b\u0f54\u0f0d'],
  QUARTERS: ['\u0f51\u0f74\u0f66\u0f0b\u0f5a\u0f72\u0f42\u0f66\u0f0b\u0f51\u0f44\u0f0b\u0f54\u0f7c\u0f0d', '\u0f51\u0f74\u0f66\u0f0b\u0f5a\u0f72\u0f42\u0f66\u0f0b\u0f42\u0f49\u0f72\u0f66\u0f0b\u0f54\u0f0d', '\u0f0b\u0f51\u0f74\u0f66\u0f0b\u0f5a\u0f72\u0f42\u0f66\u0f0b\u0f42\u0f66\u0f74\u0f58\u0f0b\u0f54\u0f0d', '\u0f51\u0f74\u0f66\u0f0b\u0f5a\u0f72\u0f42\u0f66\u0f0b\u0f56\u0f5e\u0f72\u0f0b\u0f54\u0f0d'],
  AMPMS: ['\u0f66\u0f94\u0f0b\u0f51\u0fb2\u0f7c\u0f0b', '\u0f55\u0fb1\u0f72\u0f0b\u0f51\u0fb2\u0f7c\u0f0b'],
  DATEFORMATS: ['EEEE, y MMMM dd', '\u0f66\u0fa6\u0fb1\u0f72\u0f0b\u0f63\u0f7c\u0f0by MMMM\u0f60\u0f72\u0f0b\u0f59\u0f7a\u0f66\u0f0bd\u0f51', 'y \u0f63\u0f7c\u0f0b\u0f60\u0f72\u0f0bMMM\u0f59\u0f7a\u0f66\u0f0bd', 'yyyy-MM-dd'],
  TIMEFORMATS: ['HH:mm:ss zzzz', 'HH:mm:ss z', 'HH:mm:ss', 'HH:mm'],
  FIRSTDAYOFWEEK: 6,
  WEEKENDRANGE: [6, 6],
  FIRSTWEEKCUTOFFDAY: 2
};
gadgets.i18n.DateTimeConstants.STANDALONENARROWMONTHS = gadgets.i18n.DateTimeConstants.NARROWMONTHS;
gadgets.i18n.DateTimeConstants.STANDALONEMONTHS = gadgets.i18n.DateTimeConstants.MONTHS;
gadgets.i18n.DateTimeConstants.STANDALONESHORTMONTHS = gadgets.i18n.DateTimeConstants.SHORTMONTHS;
gadgets.i18n.DateTimeConstants.STANDALONEWEEKDAYS = gadgets.i18n.DateTimeConstants.WEEKDAYS;
gadgets.i18n.DateTimeConstants.STANDALONESHORTWEEKDAYS = gadgets.i18n.DateTimeConstants.SHORTWEEKDAYS;
gadgets.i18n.DateTimeConstants.STANDALONENARROWWEEKDAYS = gadgets.i18n.DateTimeConstants.NARROWWEEKDAYS;
