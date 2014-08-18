/*
 * Copyright (C) 2010-2014 Mojang AB
 *
 * Parts of this program are marked with "VanillaIRC". These will be referred
 * to as "Modification" from this point on.
 *
 * Modification Copyright (C) 2014 MinecraftOnline
 *
 * The Modification is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The Modification is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.minecraftonline.vanillairc.ChatHandler;
import com.minecraftonline.vanillairc.VanillaIRC;
import com.mojang.authlib.GameProfile;
import io.netty.buffer.Unpooled;
import java.io.File;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public abstract class sn {

    public static final File a = new File("banned-players.json");
    public static final File b = new File("banned-ips.json");
    public static final File c = new File("ops.json");
    public static final File d = new File("whitelist.json");
    private static final Logger h = LogManager.getLogger();
    private static final SimpleDateFormat i = new SimpleDateFormat("yyyy-MM-dd \'at\' HH:mm:ss z");
    private final MinecraftServer j;
    public final List e = Lists.newArrayList();
    public final Map f = Maps.newHashMap();
    private final sv k;
    private final sd l;
    private final sp m;
    private final sx n;
    private final Map o;
    private bri p;
    private boolean q;
    protected int g;
    private int r;
    private aqz s;
    private boolean t;
    private int u;

    public sn(MinecraftServer var1) {
        this.k = new sv(a);
        this.l = new sd(b);
        this.m = new sp(c);
        this.n = new sx(d);
        this.o = Maps.newHashMap();
        this.j = var1;
        this.k.a(false);
        this.l.a(false);
        this.g = 8;
    }

    public void a(gr var1, qw var2) {
        GameProfile var3 = var2.cc();
        ry var4 = this.j.aD();
        GameProfile var5 = var4.a(var3.getId());
        String var6 = var5 == null ? var3.getName() : var5.getName();

        var4.a(var3);
        fn var7 = this.a(var2);

        var2.a((aqr) this.j.a(var2.am));
        var2.c.a((qt) var2.o);
        String var8 = "local";

        if (var1.b() != null) {
            var8 = var1.b().toString();
        }

        h.info(var2.d_() + "[" + var8 + "] logged in with entity id " + var2.F() + " at (" + var2.s + ", " + var2.t + ", " + var2.u + ")");
        qt var9 = this.j.a(var2.am);
        bql var10 = var9.P();
        dt var11 = var9.M();

        this.a(var2, (qw) null, var9);
        rj var12 = new rj(this.j, var1, var2);

        var12.a((id) (new jw(var2.F(), var2.c.b(), var10.t(), var9.t.r(), var9.aa(), this.q(), var10.u(), var9.Q().b("reducedDebugInfo"))));
        var12.a((id) (new ji("MC|Brand", (new hd(Unpooled.buffer())).a(this.c().getServerModName()))));
        var12.a((id) (new ix(var10.y(), var10.z())));
        var12.a((id) (new lh(var11)));
        var12.a((id) (new kd(var2.by)));
        var12.a((id) (new kv(var2.bg.c)));
        var2.A().d();
        var2.A().b(var2);
        this.a((pk) var9.Z(), var2);
        this.j.aF();
        hz var13;

        if (!var2.d_().equalsIgnoreCase(var6)) {
            var13 = new hz("multiplayer.player.joined.renamed", new Object[] { var2.e_(), var6});
        } else {
            var13 = new hz("multiplayer.player.joined", new Object[] { var2.e_()});
        }

        // VanillaIRC start - field "a" blocks class "a"
        try {
            var13.b().a((a) Class.forName("a").getDeclaredField("o").get(null));
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        } // VanillaIRC end
        this.a((ho) var13);
        this.c(var2);
        var12.a(var2.s, var2.t, var2.u, var2.y, var2.z);
        this.b(var2, var9);
        if (this.j.aa().length() > 0) {
            var2.a(this.j.aa(), this.j.ab());
        }

        Iterator var14 = var2.bk().iterator();

        while (var14.hasNext()) {
            wq var15 = (wq) var14.next();

            var12.a((id) (new lr(var2.F(), var15)));
        }

        var2.f_();
        if (var7 != null && var7.b("Riding", 10)) {
            wv var16 = xb.a(var7.m("Riding"), (aqr) var9);

            if (var16 != null) {
                var16.n = true;
                var9.d(var16);
                var2.a(var16);
                var16.n = false;
            }
        }

    }

    protected void a(pk var1, qw var2) {
        HashSet var3 = Sets.newHashSet();
        Iterator var4 = var1.g().iterator();

        while (var4.hasNext()) {
            brw var5 = (brw) var4.next();

            var2.a.a((id) (new le(var5, 0)));
        }

        for (int var9 = 0; var9 < 19; ++var9) {
            brv var10 = var1.a(var9);

            if (var10 != null && !var3.contains(var10)) {
                List var6 = var1.d(var10);
                Iterator var7 = var6.iterator();

                while (var7.hasNext()) {
                    id var8 = (id) var7.next();

                    var2.a.a(var8);
                }

                var3.add(var10);
            }
        }

    }

    public void a(qt[] var1) {
        this.p = var1[0].O().e();
        var1[0].af().a((bew) (new so(this)));
    }

    public void a(qw var1, qt var2) {
        qt var3 = var1.u();

        if (var2 != null) {
            var2.t().c(var1);
        }

        var3.t().a(var1);
        var3.b.c((int) var1.s >> 4, (int) var1.u >> 4);
    }

    public int d() {
        return qq.b(this.t());
    }

    public fn a(qw var1) {
        fn var2 = this.j.c[0].P().i();
        fn var3;

        if (var1.d_().equals(this.j.R()) && var2 != null) {
            var1.f(var2);
            var3 = var2;
            h.debug("loading single player");
        } else {
            var3 = this.p.b(var1);
        }

        return var3;
    }

    protected void b(qw var1) {
        this.p.a(var1);
        tp var2 = (tp) this.o.get(var1.aJ());

        if (var2 != null) {
            var2.b();
        }

    }

    public void c(qw var1) {
        this.e.add(var1);
        this.f.put(var1.aJ(), var1);
        this.a((id) (new kh(kj.a, new qw[] { var1})));
        qt var2 = this.j.a(var1.am);

        var2.d(var1);
        this.a(var1, (qt) null);

        for (int var3 = 0; var3 < this.e.size(); ++var3) {
            qw var4 = (qw) this.e.get(var3);

            var1.a.a((id) (new kh(kj.a, new qw[] { var4})));
        }

    }

    public void d(qw var1) {
        var1.u().t().d(var1);
    }

    public void e(qw var1) {
        var1.b(ty.f);
        this.b(var1);
        qt var2 = var1.u();

        if (var1.m != null) {
            var2.f(var1.m);
            h.debug("removing player mount");
        }

        var2.e(var1);
        var2.t().c(var1);
        this.e.remove(var1);
        this.f.remove(var1.aJ());
        this.o.remove(var1.aJ());
        this.a((id) (new kh(kj.e, new qw[] { var1})));
    }

    public String a(SocketAddress var1, GameProfile var2) {
        String var4;

        if (this.k.a(var2)) {
            sw var5 = (sw) this.k.b((Object) var2); // VanillaIRC - cast to Object

            var4 = "You are banned from this server!\nReason: " + var5.d();
            if (var5.c() != null) {
                var4 = var4 + "\nYour ban will be removed on " + i.format(var5.c());
            }

            return var4;
        } else if (!this.e(var2)) {
            return "You are not white-listed on this server!";
        } else if (this.l.a(var1)) {
            se var3 = this.l.b(var1);

            var4 = "Your IP address is banned from this server!\nReason: " + var3.d();
            if (var3.c() != null) {
                var4 = var4 + "\nYour ban will be removed on " + i.format(var3.c());
            }

            return var4;
        } else {
            return this.e.size() >= this.g ? "The server is full!" : null;
        }
    }

    public qw f(GameProfile var1) {
        UUID var2 = aha.a(var1);
        ArrayList var3 = Lists.newArrayList();

        qw var5;

        for (int var4 = 0; var4 < this.e.size(); ++var4) {
            var5 = (qw) this.e.get(var4);
            if (var5.aJ().equals(var2)) {
                var3.add(var5);
            }
        }

        Iterator var6 = var3.iterator();

        while (var6.hasNext()) {
            var5 = (qw) var6.next();
            var5.a.c("You logged in from another location");
        }

        Object var7;

        if (this.j.W()) {
            var7 = new qk(this.j.a(0));
        } else {
            var7 = new qx(this.j.a(0));
        }

        return new qw(this.j, this.j.a(0), var1, (qx) var7);
    }

    public qw a(qw var1, int var2, boolean var3) {
        var1.u().s().b(var1);
        var1.u().s().b((wv) var1);
        var1.u().t().c(var1);
        this.e.remove(var1);
        this.j.a(var1.am).f(var1);
        dt var4 = var1.cg();
        boolean var5 = var1.ch();

        var1.am = var2;
        Object var6;

        if (this.j.W()) {
            var6 = new qk(this.j.a(var1.am));
        } else {
            var6 = new qx(this.j.a(var1.am));
        }

        qw var7 = new qw(this.j, this.j.a(var1.am), var1.cc(), (qx) var6);

        var7.a = var1.a;
        var7.a((aha) var1, var3);
        var7.d(var1.F());
        var7.o(var1);
        qt var8 = this.j.a(var1.am);

        this.a(var7, var1, var8);
        dt var9;

        if (var4 != null) {
            var9 = aha.a(this.j.a(var1.am), var4, var5);
            if (var9 != null) {
                var7.b((double) ((float) var9.n() + 0.5F), (double) ((float) var9.o() + 0.1F), (double) ((float) var9.p() + 0.5F), 0.0F, 0.0F);
                var7.a(var4, var5);
            } else {
                var7.a.a((id) (new jo(0, 0.0F)));
            }
        }

        var8.b.c((int) var7.s >> 4, (int) var7.u >> 4);

        while (!var8.a((wv) var7, var7.aQ()).isEmpty() && var7.t < 256.0D) {
            var7.b(var7.s, var7.t + 1.0D, var7.u);
        }

        var7.a.a((id) (new kp(var7.am, var7.o.aa(), var7.o.P().u(), var7.c.b())));
        var9 = var8.M();
        var7.a.a(var7.s, var7.t, var7.u, var7.y, var7.z);
        var7.a.a((id) (new lh(var9)));
        var7.a.a((id) (new lb(var7.bB, var7.bA, var7.bz)));
        this.b(var7, var8);
        var8.t().a(var7);
        var8.d(var7);
        this.e.add(var7);
        this.f.put(var7.aJ(), var7);
        var7.f_();
        var7.h(var7.bm());
        return var7;
    }

    public void a(qw var1, int var2) {
        int var3 = var1.am;
        qt var4 = this.j.a(var1.am);

        var1.am = var2;
        qt var5 = this.j.a(var1.am);

        var1.a.a((id) (new kp(var1.am, var1.o.aa(), var1.o.P().u(), var1.c.b())));
        var4.f(var1);
        var1.I = false;
        this.a(var1, var3, var4, var5);
        this.a(var1, var4);
        var1.a.a(var1.s, var1.t, var1.u, var1.y, var1.z);
        var1.c.a(var5);
        this.b(var1, var5);
        this.f(var1);
        Iterator var6 = var1.bk().iterator();

        while (var6.hasNext()) {
            wq var7 = (wq) var6.next();

            var1.a.a((id) (new lr(var1.F(), var7)));
        }

    }

    public void a(wv var1, int var2, qt var3, qt var4) {
        double var5 = var1.s;
        double var7 = var1.u;
        double var9 = 8.0D;
        float var11 = var1.y;

        var3.B.a("moving");
        if (var1.am == -1) {
            var5 = uv.a(var5 / var9, var4.af().b() + 16.0D, var4.af().d() - 16.0D);
            var7 = uv.a(var7 / var9, var4.af().c() + 16.0D, var4.af().e() - 16.0D);
            var1.b(var5, var1.t, var7, var1.y, var1.z);
            if (var1.ai()) {
                var3.a(var1, false);
            }
        } else if (var1.am == 0) {
            var5 = uv.a(var5 * var9, var4.af().b() + 16.0D, var4.af().d() - 16.0D);
            var7 = uv.a(var7 * var9, var4.af().c() + 16.0D, var4.af().e() - 16.0D);
            var1.b(var5, var1.t, var7, var1.y, var1.z);
            if (var1.ai()) {
                var3.a(var1, false);
            }
        } else {
            dt var12;

            if (var2 == 1) {
                var12 = var4.M();
            } else {
                var12 = var4.m();
            }

            var5 = (double) var12.n();
            var1.t = (double) var12.o();
            var7 = (double) var12.p();
            var1.b(var5, var1.t, var7, 90.0F, 0.0F);
            if (var1.ai()) {
                var3.a(var1, false);
            }
        }

        var3.B.b();
        if (var2 != 1) {
            var3.B.a("placing");
            var5 = (double) uv.a((int) var5, -29999872, 29999872);
            var7 = (double) uv.a((int) var7, -29999872, 29999872);
            if (var1.ai()) {
                var1.b(var5, var1.t, var7, var1.y, var1.z);
                var4.u().a(var1, var11);
                var4.d(var1);
                var4.a(var1, false);
            }

            var3.B.b();
        }

        var1.a((aqr) var4);
    }

    public void e() {
        if (++this.u > 600) {
            this.a((id) (new kh(kj.c, this.e)));
            this.u = 0;
        }

    }

    public void a(id var1) {
        for (int var2 = 0; var2 < this.e.size(); ++var2) {
            ((qw) this.e.get(var2)).a.a(var1);
        }

    }

    public void a(id var1, int var2) {
        for (int var3 = 0; var3 < this.e.size(); ++var3) {
            qw var4 = (qw) this.e.get(var3);

            if (var4.am == var2) {
                var4.a.a(var1);
            }
        }

    }

    public void a(aha var1, ho var2) {
        bsc var3 = var1.bN();

        if (var3 != null) {
            Collection var4 = var3.d();
            Iterator var5 = var4.iterator();

            while (var5.hasNext()) {
                String var6 = (String) var5.next();
                qw var7 = this.a(var6);

                if (var7 != null && var7 != var1) {
                    var7.a(var2);
                }
            }

        }
    }

    public void b(aha var1, ho var2) {
        bsc var3 = var1.bN();

        if (var3 == null) {
            this.a(var2);
        } else {
            for (int var4 = 0; var4 < this.e.size(); ++var4) {
                qw var5 = (qw) this.e.get(var4);

                if (var5.bN() != var3) {
                    var5.a(var2);
                }
            }

        }
    }

    public String f() {
        String var1 = "";

        for (int var2 = 0; var2 < this.e.size(); ++var2) {
            if (var2 > 0) {
                var1 = var1 + ", ";
            }

            var1 = var1 + ((qw) this.e.get(var2)).d_();
        }

        return var1;
    }

    public String[] g() {
        String[] var1 = new String[this.e.size()];

        for (int var2 = 0; var2 < this.e.size(); ++var2) {
            var1[var2] = ((qw) this.e.get(var2)).d_();
        }

        return var1;
    }

    public GameProfile[] h() {
        GameProfile[] var1 = new GameProfile[this.e.size()];

        for (int var2 = 0; var2 < this.e.size(); ++var2) {
            var1[var2] = ((qw) this.e.get(var2)).cc();
        }

        return var1;
    }

    public sv i() {
        return this.k;
    }

    public sd j() {
        return this.l;
    }

    public void a(GameProfile var1) {
        this.m.a((sr) (new sq(var1, this.j.p())));
    }

    public void b(GameProfile var1) {
        this.m.c(var1);
    }

    public boolean e(GameProfile var1) {
        return !this.q || this.m.d(var1) || this.n.d(var1);
    }

    public boolean g(GameProfile var1) {
        return this.m.d(var1) || this.j.S() && this.j.c[0].P().v() && this.j.R().equalsIgnoreCase(var1.getName()) || this.t;
    }

    public qw a(String var1) {
        Iterator var2 = this.e.iterator();

        qw var3;

        do {
            if (!var2.hasNext()) {
                return null;
            }

            var3 = (qw) var2.next();
        } while (!var3.d_().equalsIgnoreCase(var1));

        return var3;
    }

    public void a(double var1, double var3, double var5, double var7, int var9, id var10) {
        this.a((aha) null, var1, var3, var5, var7, var9, var10);
    }

    public void a(aha var1, double var2, double var4, double var6, double var8, int var10, id var11) {
        for (int var12 = 0; var12 < this.e.size(); ++var12) {
            qw var13 = (qw) this.e.get(var12);

            if (var13 != var1 && var13.am == var10) {
                double var14 = var2 - var13.s;
                double var16 = var4 - var13.t;
                double var18 = var6 - var13.u;

                if (var14 * var14 + var16 * var16 + var18 * var18 < var8 * var8) {
                    var13.a.a(var11);
                }
            }
        }

    }

    public void k() {
        for (int var1 = 0; var1 < this.e.size(); ++var1) {
            this.b((qw) this.e.get(var1));
        }

    }

    public void d(GameProfile var1) {
        this.n.a((sr) (new sy(var1)));
    }

    public void c(GameProfile var1) {
        this.n.c(var1);
    }

    public sx l() {
        return this.n;
    }

    public String[] m() {
        return this.n.a();
    }

    public sp n() {
        return this.m;
    }

    public String[] o() {
        return this.m.a();
    }

    public void a() {}

    public void b(qw var1, qt var2) {
        bey var3 = this.j.c[0].af();

        var1.a.a((id) (new kr(var3, kt.d)));
        var1.a.a((id) (new li(var2.K(), var2.L(), var2.Q().b("doDaylightCycle"))));
        if (var2.S()) {
            var1.a.a((id) (new jo(1, 0.0F)));
            var1.a.a((id) (new jo(7, var2.j(1.0F))));
            var1.a.a((id) (new jo(8, var2.h(1.0F))));
        }

    }

    public void f(qw var1) {
        var1.a(var1.bh);
        var1.r();
        var1.a.a((id) (new kv(var1.bg.c)));
    }

    public int p() {
        return this.e.size();
    }

    public int q() {
        return this.g;
    }

    public String[] r() {
        return this.j.c[0].O().e().f();
    }

    public boolean s() {
        return this.q;
    }

    public void a(boolean var1) {
        this.q = var1;
    }

    public List b(String var1) {
        ArrayList var2 = Lists.newArrayList();
        Iterator var3 = this.e.iterator();

        while (var3.hasNext()) {
            qw var4 = (qw) var3.next();

            if (var4.w().equals(var1)) {
                var2.add(var4);
            }
        }

        return var2;
    }

    public int t() {
        return this.r;
    }

    public MinecraftServer c() {
        return this.j;
    }

    public fn u() {
        return null;
    }

    private void a(qw var1, qw var2, aqr var3) {
        if (var2 != null) {
            var1.c.a(var2.c.b());
        } else if (this.s != null) {
            var1.c.a(this.s);
        }

        var1.c.b(var3.P().r());
    }

    public void v() {
        for (int var1 = 0; var1 < this.e.size(); ++var1) {
            ((qw) this.e.get(var1)).a.c("Server closed");
        }

    }

    // VanillaIRC start -- redirect for chat injection
    public void a(ho var1, boolean var2) {
        this.sendMessageToPlayers(var1, var2);

        String translated = var1.c();
        ChatHandler ch = VanillaIRC.getHandler();
        if (ch != null) {
            ch.handleGameMessage(translated);
        }
    }

    private void sendMessageToPlayers(ho var1, boolean var2) {
        this.j.a(var1);
        int var3 = var2 ? 1 : 0;

        this.a((id) (new iz(var1, (byte) var3)));
    }

    public void sendFromJson(String json) {
        this.sendMessageToPlayers(hp.a(json), false);
    } // VanillaIRC end

    public void a(ho var1) {
        this.a(var1, true);
    }

    public tp a(aha var1) {
        UUID var2 = var1.aJ();
        tp var3 = var2 == null ? null : (tp) this.o.get(var2);

        if (var3 == null) {
            File var4 = new File(this.j.a(0).O().b(), "stats");
            File var5 = new File(var4, var2.toString() + ".json");

            if (!var5.exists()) {
                File var6 = new File(var4, var1.d_() + ".json");

                if (var6.exists() && var6.isFile()) {
                    var6.renameTo(var5);
                }
            }

            var3 = new tp(this.j, var5);
            var3.a();
            this.o.put(var2, var3);
        }

        return var3;
    }

    public void a(int var1) {
        this.r = var1;
        if (this.j.c != null) {
            qt[] var2 = this.j.c;
            int var3 = var2.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                qt var5 = var2[var4];

                if (var5 != null) {
                    var5.t().a(var1);
                }
            }

        }
    }

    public qw a(UUID var1) {
        return (qw) this.f.get(var1);
    }

}
